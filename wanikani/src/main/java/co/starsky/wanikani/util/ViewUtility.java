package co.starsky.wanikani.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;
import co.starsky.wanikani.R;

/**
 * @author alliecurry
 */
public final class ViewUtility {
    private static final int PROGRESS_COLOR = R.color.cerulean;

    private ViewUtility() {
        throw new AssertionError();
    }

    public static ProgressDialog getDefaultProgressDialog(final Context c) {
        final ProgressDialog progressDialog = new ProgressDialog(c);
        progressDialog.setCancelable(false);
        final int color = c.getResources().getColor(PROGRESS_COLOR);
        progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progressDialog.setContentView(R.layout.progress_dialog);
                final ProgressBar v = (ProgressBar) progressDialog.findViewById(android.R.id.progress);
                v.getIndeterminateDrawable().setColorFilter(color,
                        android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        });
        return progressDialog;
    }

}
