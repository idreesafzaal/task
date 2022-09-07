package e.visiontech.luncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import e.visiontech.luncher.Adpters.AppsAdpter;
import e.visiontech.luncher.DataModels.AppDataModel;

public class AppContainer extends AppCompatActivity
{
    RecyclerView appRecylerview;
    List<AppDataModel>appDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_container);
        appDataModelList=new ArrayList<>();
        getViews();
    }

    private void getViews()
    {
        appDataModelList=getInstalledApps();
        appRecylerview=findViewById(R.id.appRecyclerView);
        appRecylerview.setLayoutManager(new GridLayoutManager(AppContainer.this,4));
        appRecylerview.setAdapter(new AppsAdpter(AppContainer.this,appDataModelList));
    }

    private List<AppDataModel> getInstalledApps()
    {
        List<AppDataModel> list=new ArrayList<>();
        Intent intent=new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList=getApplicationContext().getPackageManager().queryIntentActivities(intent,0);
        for ( ResolveInfo app: appList)
        {
            String appName=app.activityInfo.loadLabel(getPackageManager()).toString();
            String appPackageName=app.activityInfo.packageName;
            Drawable appImage=app.activityInfo.loadIcon(getPackageManager());
            AppDataModel appDataModel=new AppDataModel(appName,appPackageName,appImage);
            if(!list.contains(appDataModel))
                list.add(appDataModel);

        }
        return list;
    }
}