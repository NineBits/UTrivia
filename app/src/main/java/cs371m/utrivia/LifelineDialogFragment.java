package cs371m.utrivia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
public class LifelineDialogFragment extends DialogFragment {
    static LifelineDialogFragment newInstance() {
        return new LifelineDialogFragment();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Lifeline Dialog")
                .setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                                dismiss();
                            }
                        })
                .setNegativeButton("no",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dismiss();
                            }
                        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}