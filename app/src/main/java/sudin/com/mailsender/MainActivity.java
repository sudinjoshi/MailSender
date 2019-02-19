package sudin.com.mailsender;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.send);

        /*button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                try
                {
                    GMailSender sender = new GMailSender("sudinjoshi13@gmail.com", "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "sudinjoshi13@gmail.com",
                            "sudinjoshi13@gmail.com");
                } catch (Exception e)
                {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });*/

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("sendersemail@gmail.com", "password");
                    sender.sendMail("EmailSender App",
                            "This is the message body",
                            "sendersemail@gmail.com",
                            "receiversemail@gmail.com");
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

}
