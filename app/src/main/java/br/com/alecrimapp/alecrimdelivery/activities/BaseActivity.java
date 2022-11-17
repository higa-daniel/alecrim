package br.com.alecrimapp.alecrimdelivery.activities;

import android.app.ProgressDialog;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by ricardomiranda on 08/01/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog progressDialog = null;

    public void showThrobber(){
        this.hideThrobber();

        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Carregando...");
        this.progressDialog.setMessage("Aguarde um instante");
        this.progressDialog.show();

    }

    public void hideThrobber(){
        if (this.progressDialog != null){
            if (this.progressDialog.isShowing())
                this.progressDialog.dismiss();

            this.progressDialog = null;
        }
    }

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(@IdRes int containerViewId,
                                @NonNull Fragment fragment,
                                @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(backStackStateName)
                .commit();
    }

    public void showToast(@NonNull String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showToast(@NonNull int resId){
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }
}
