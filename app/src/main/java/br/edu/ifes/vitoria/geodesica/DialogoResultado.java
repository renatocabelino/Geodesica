package br.edu.ifes.vitoria.geodesica;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("ValidFragment")
class DialogoResultado extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        float mAzimute = 0;
        mAzimute = getArguments().getFloat("azimute");
        // Use the Builder class because this dialog has a simple UI
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Dialog will have "Make a selection" as the title
        builder.setMessage("Resultado do Cálculo: " + mAzimute)// An OK button that does nothing
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Nothing happening here
                    }
        });
        // Create the object and return it
        return builder.create();
    }// End onCreateDialog
}
