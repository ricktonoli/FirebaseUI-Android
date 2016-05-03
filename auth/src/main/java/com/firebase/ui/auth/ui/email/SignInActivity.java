package com.firebase.ui.auth.ui.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.R;
import com.firebase.ui.auth.choreographer.ControllerConstants;
import com.firebase.ui.auth.choreographer.email.EmailFlowController;

public class SignInActivity extends EmailFlowBaseActivity implements View.OnClickListener {
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.sign_in);
        mId = EmailFlowController.ID_SIGN_IN;
        setContentView(R.layout.sign_in_layout);

        String email = getIntent().getStringExtra(ControllerConstants.EXTRA_EMAIL);

        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);

        Button signInButton = (Button) findViewById(R.id.button_done);
        TextView recoveryButton =  (TextView) findViewById(R.id.trouble_signing_in);

        if(email != null) {
            mEmailEditText.setText(email);
        }
        signInButton.setOnClickListener(this);
        recoveryButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
    }


    @Override
    public void onClick(View view) {
        if( super.isPendingFinishing.get()) {
            return;
        }
        if (view.getId() == R.id.button_done) {
            if (mEmailEditText.getText().toString().equalsIgnoreCase("")
                    || mPasswordEditText.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(this, "Missing password or email", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Intent data = new Intent();
                data.putExtra(ControllerConstants.EXTRA_EMAIL,
                        mEmailEditText.getText().toString());
                data.putExtra(ControllerConstants.EXTRA_PASSWORD,
                        mPasswordEditText.getText().toString());
                data.putExtra(ControllerConstants.EXTRA_RESTORE_PASSWORD_FLAG, false);
                finish(RESULT_OK, data);
                return;
            }
        } else if (view.getId() == R.id.trouble_signing_in) {
            Intent data = new Intent();
            data.putExtra(ControllerConstants.EXTRA_EMAIL, mEmailEditText.getText().toString());
            data.putExtra(ControllerConstants.EXTRA_RESTORE_PASSWORD_FLAG, true);
            finish(RESULT_OK, data);
            return;
        }
    }
}