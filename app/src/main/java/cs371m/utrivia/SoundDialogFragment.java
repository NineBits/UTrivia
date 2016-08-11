package cs371m.utrivia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class SoundDialogFragment extends DialogFragment {

    private static final String SOUND_KEY = "mute";

    /**
     * Create a new instance of Difficulty, initialized to
     * show the current difficulty
     */
    public static SoundDialogFragment newInstance(int sound) {
        SoundDialogFragment result = new SoundDialogFragment();

        // Supply difficulty input as an argument.
        Bundle args = new Bundle();
        args.putInt(SOUND_KEY , sound);
        result.setArguments(args);

        return result;
    }

    public int getSelectedSound() {
        return getArguments().getInt(SOUND_KEY, 0);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int currentDifficulty = getSelectedSound();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(R.string.mute_sound)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(R.array.sound_choice, currentDifficulty,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // save the difficulty
                                getArguments().putInt(SOUND_KEY, which);
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // user pressed okay, so we are going to change the difficulty
                        int soundStatus = getArguments().getInt(SOUND_KEY, 0);
                        ((BaseMenuActivity)getActivity()).setSoundStatus(soundStatus);

                       // ((AndroidTicTacToe)getActivity()).setDifficulty(newDifficulty);
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // don't change difficulty
                        dismiss();
                    }
                });

        return builder.create();
    }
}
