package cs371m.utrivia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by kellypc on 8/10/2016.
 */
public class BonusDialogFragment extends DialogFragment {
    static BonusDialogFragment newInstance() {
        return new BonusDialogFragment();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Would you like to answer the Bonus Question?")
                .setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((Questionnaire)getActivity()).setBonus();
                                //((Questionnaire)getActivity()).bonus_used = true;
                                dismiss();
                            }
                        })
                .setNegativeButton("no",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((Questionnaire)getActivity()).bonus_used = true;
                                dismiss();
                            }
                        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
