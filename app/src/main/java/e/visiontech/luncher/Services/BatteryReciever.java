package e.visiontech.luncher.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import e.visiontech.luncher.MainActivity;
import e.visiontech.luncher.R;

public class BatteryReciever extends BroadcastReceiver
{



    @Override
    public void onReceive(Context context, Intent intent) {


        TextView statusLabel = ((MainActivity)context).findViewById(R.id.tvStatus);
        TextView percentageLabel = ((MainActivity) context).findViewById(R.id.tvPercentage);
        ImageView batteryImage = ((MainActivity) context).findViewById(R.id.batteryImage);


        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            // Status
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String message = "";


            switch (status)
            {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "Full";
                    statusLabel.setText(message);
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "Charging";
                    statusLabel.setText(message);

                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharging";
                    statusLabel.setText(message);
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not charging";
                    statusLabel.setText(message);
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "Unknown";
                    statusLabel.setText(message);
                    break;
            }



            // Percentage
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = level * 100 / scale;
            percentageLabel.setText(percentage + "%");


            // Image
            Resources res = context.getResources();
            // ContextCompat.getDrawable(getActivity(), R.drawable.drawable_resource_name);

            if (percentage >= 90) {

               // batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100)); this is depracted function that why use below line
                batteryImage.setImageDrawable(ContextCompat.getDrawable(((MainActivity) context), R.drawable.b100));

            } else if (90 > percentage && percentage >= 65) {

                // batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75));
                batteryImage.setImageDrawable(ContextCompat.getDrawable(((MainActivity) context), R.drawable.b75));

            } else if (65 > percentage && percentage >= 40) {

                //batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50));
                batteryImage.setImageDrawable(ContextCompat.getDrawable(((MainActivity) context), R.drawable.b50));

            } else if (40 > percentage && percentage >= 15) {

                //batteryImage.setImageDrawable(res.getDrawable(R.drawable.b25));
               batteryImage.setImageDrawable(ContextCompat.getDrawable(((MainActivity) context), R.drawable.b25));

            } else {
               // batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0));
                batteryImage.setImageDrawable(ContextCompat.getDrawable(((MainActivity) context), R.drawable.b0));

            }

        }
    }
}
