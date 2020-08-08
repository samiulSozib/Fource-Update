package com.example.fourceupdatefirst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckerListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateHelper.with(this).onUpdateCheck(this).check();

    }

    @Override
    public void onUpdateCheckerListener(final String urlApp) {

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("New Version Available")
                .setMessage("Please Update for continue for new version use")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(getApplicationContext(),""+urlApp,Toast.LENGTH_SHORT).show();
                        redirectStore(urlApp);
                    }
                })
                /*.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })*/
                .create();


        alertDialog.setCancelable(false);

        alertDialog.show();


    }
    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
