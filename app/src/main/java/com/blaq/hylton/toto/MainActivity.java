package com.blaq.hylton.toto;

import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SectionPageManager mSectionPageManager;
    private ViewPager mPager;
    private TabLayout mTabLayout;

    private FloatingActionButton fab;

    private int [] colorIntArray;
    private int [] iconIntArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorIntArray = new int[]{R.color.walking, R.color.golfing};
        iconIntArray = new int[]{R.drawable.ic_add_black, R.drawable.ic_add_black};

        mPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tabs);
        fab = findViewById(R.id.fab);

        setupViewPager(mPager);
        mTabLayout.setupWithViewPager(mPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
                animateFab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
                Toast.makeText(MainActivity.this, "Fab worked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupViewPager(ViewPager viewPager)
    {
        mSectionPageManager = new SectionPageManager(getSupportFragmentManager());
        mSectionPageManager.addAdapter(new TodayFragment(), "Today");
        mSectionPageManager.addAdapter(new TomorrowFragment(), "Tomorrow");

        viewPager.setAdapter(mSectionPageManager);
    }


    protected void animateFab(final int position)
    {
        fab.clearAnimation();
        // Scale down animation
        ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Change FAB color and icon
                fab.setBackgroundTintList(getResources().getColorStateList(colorIntArray[position]));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    fab.setImageDrawable(getResources().getDrawable(iconIntArray[position], null));
                }

                // Scale up animation
                ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);     // animation duration in milliseconds
                expand.setInterpolator(new AccelerateInterpolator());
                fab.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.startAnimation(shrink);
    }

    public void addTask()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();

        builder.setView(layoutInflater.inflate(R.layout.add_task_dialog, null))
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Dialogue worked", Toast.LENGTH_SHORT).show();
                        //saveTo DataBase()
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        //Do Nothing when cancel is pressed
                    }
                });

        builder.setTitle("Add Task");
        builder.show();
    }
}
