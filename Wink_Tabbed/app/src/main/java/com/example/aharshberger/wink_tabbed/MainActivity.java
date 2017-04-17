package com.example.aharshberger.wink_tabbed;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int count_person1 = 1;
    private int count_person2 = 1;
    private int string_count_int_person_1, string_count_int_person_2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor, editor_names;
    private String person_1_name_saved, person_2_name_saved;
    private TextView person_1, person_2, person_1_count, person_2_count;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    public void clickingHelp(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.setIcon(R.mipmap.ic_wink);
        builder1.setTitle("Needed help huh?");
        builder1.setMessage("To play, just change the name of each player and start adding up the winks.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Sounds good!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply();

    }

    public void clicking_wink_person_1(View view) {

        person_1_count = (TextView) findViewById(R.id.Person_1_Count);

        //Adding logic to add shared pref to the incrementer

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("MyPref", true)) {

            int person_1_count_shared_to_add = pref.getInt("Saved_person_1_winks", 0);

            String string_count = String.valueOf(person_1_count_shared_to_add + 1);

            person_1_count.setText(string_count);

            string_count_int_person_1 = Integer.valueOf(string_count);

            editor.putInt("Saved_person_1_winks", string_count_int_person_1);

            editor.commit();

            Log.d("Number in the editor", String.valueOf(string_count_int_person_1));


        } else {
            String string_count = String.valueOf(count_person1++);

            person_1_count.setText(string_count);

            //////Shared Prefs

            string_count_int_person_1 = Integer.valueOf(string_count);

            editor.putInt("Saved_person_1_winks", string_count_int_person_1);

            editor.commit();

            Log.d("Number in the editor", String.valueOf(string_count_int_person_1));

        }


    }

    public void clicking_wink_person_2(View view) {

        person_2_count = (TextView) findViewById(R.id.Person_2_Count);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("MyPref", true)) {

            int person_2_count_shared_to_add = pref.getInt("Saved_person_2_winks", 0);

            String string_count = String.valueOf(person_2_count_shared_to_add + 1);

            person_2_count.setText(string_count);

            string_count_int_person_2 = Integer.valueOf(string_count);

            editor.putInt("Saved_person_2_winks", string_count_int_person_2);

            editor.commit();

            Log.d("Number in the editor", String.valueOf(string_count_int_person_2));

        } else {


            String string_count = String.valueOf(count_person2++);

            person_2_count.setText(string_count);

            // Trying Shared Prefs
            string_count_int_person_2 = Integer.valueOf(string_count);

            editor.putInt("Saved_person_2_winks", string_count_int_person_2);

            editor.commit();

            Log.d("Number in the editor", String.valueOf(string_count_int_person_2));
        }


    }

    public void clicking_reset_person_1(View view) {


        person_1_count = (TextView) findViewById(R.id.Person_1_Count);

        count_person1 = 0;

        String string_count_reset_person1 = String.valueOf(count_person1);

        person_1_count.setText(string_count_reset_person1);

        editor.clear();
        editor.commit();
    }

    public void clicking_reset_person_2(View view) {

        person_2_count = (TextView) findViewById(R.id.Person_2_Count);

        count_person2 = 0;

        String string_count_reset_person2 = String.valueOf(count_person2);

        person_2_count.setText(string_count_reset_person2);

        editor.clear();
        editor.commit();


    }

    public void clicking_change_player_2(View view) {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edittextdiag, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text

                                person_2.setText(userInput.getText());

                                // Trying Shared Prefs
                                person_2_name_saved = userInput.getText().toString();

                                editor.putString("Saved_person_2_name", person_2_name_saved);

                                editor.commit();

                                Log.d("Changed name to: ", person_2_name_saved);
                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }

    public void clicking_change_player_1(View view) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edittextdiag, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text

                                person_1.setText(userInput.getText());

                                // Trying Shared Prefs
                                person_1_name_saved = userInput.getText().toString();

                                editor.putString("Saved_person_1_name", person_1_name_saved);

                                editor.commit();

                                Log.d("Changed name to: ", person_1_name_saved);
                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void clickingTextViewP1(View view) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edittextdiag, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text

                                person_1.setText(userInput.getText());

                                // Trying Shared Prefs
                                person_1_name_saved = userInput.getText().toString();

                                editor.putString("Saved_person_1_name", person_1_name_saved);

                                editor.commit();

                                Log.d("Changed name to: ", person_1_name_saved);
                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void clickingTextViewP2(View view) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edittextdiag, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text

                                person_2.setText(userInput.getText());

                                // Trying Shared Prefs
                                person_2_name_saved = userInput.getText().toString();

                                editor.putString("Saved_person_2_name", person_2_name_saved);

                                editor.commit();

                                Log.d("Changed name to: ", person_2_name_saved);
                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void clickingSmiley(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.setIcon(R.mipmap.ic_wink);
        builder1.setTitle(" ");
        builder1.setMessage(pref.getString("Saved_person_1_name", "Player 1") + " Vs. " + pref.getString("Saved_person_2_name", "Player 2"));
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Let's go!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


