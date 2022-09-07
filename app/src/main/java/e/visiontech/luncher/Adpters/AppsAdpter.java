package e.visiontech.luncher.Adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import e.visiontech.luncher.DataModels.AppDataModel;
import e.visiontech.luncher.R;

public class AppsAdpter extends RecyclerView.Adapter<AppsAdpter.ViewHolder>
{
    Context context;
    List<AppDataModel> appDataModelList;

    public AppsAdpter(Context context, List<AppDataModel> appDataModelList)
    {
        this.context=context;
        this.appDataModelList=appDataModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.app_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.appImages.setImageDrawable(appDataModelList.get(position).getImage());
        holder.appNames.setText(appDataModelList.get(position).getName());

        holder.lnApp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent appLuncher =context.getPackageManager().getLaunchIntentForPackage(appDataModelList.get(position).getPackageName());
                if (appLuncher!=null)
                    context.startActivity(appLuncher);

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return appDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView appNames;
        ImageView appImages;
        RelativeLayout lnApp;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            lnApp=itemView.findViewById(R.id.lnApp);
            appImages=itemView.findViewById(R.id.appItemsIv);
            appNames=itemView.findViewById(R.id.tvAppName);

        }
    }
}
