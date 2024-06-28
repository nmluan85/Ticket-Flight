package com.example.ticket_flight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class OnBoarding extends AppCompatActivity{
    ImageView slider;
    ImageView illustration;
    ImageButton button_next;
    int currentPage = 0;
    List<Integer> imageResourceSlider;
    List<Integer> imageResourceIllustration;
    List<Integer> imageResourceButton;

    static int timeFade = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding);

        slider = findViewById(R.id.slider);
        illustration = findViewById(R.id.illustration);
        button_next = findViewById(R.id.button_next);

        imageResourceSlider = new ArrayList<>();
        imageResourceSlider.add(R.drawable.slider_1);
        imageResourceSlider.add(R.drawable.slider_2);
        imageResourceSlider.add(R.drawable.slider_3);

        imageResourceIllustration = new ArrayList<>();
        imageResourceIllustration.add(R.drawable.illustration_1);
        imageResourceIllustration.add(R.drawable.illustration_2);
        imageResourceIllustration.add(R.drawable.illustration_3);

        imageResourceButton = new ArrayList<>();
        imageResourceButton.add(R.drawable.button_next_1);
        imageResourceButton.add(R.drawable.button_next_2);
        imageResourceButton.add(R.drawable.button_next_3);

        updatePage(currentPage);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < imageResourceSlider.size() - 1){
                    currentPage++;
                    updatePage(currentPage);
                    animateTrasaction(currentPage);
                }
                else {
                    Intent i = new Intent(OnBoarding.this, Welcome.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private void animateTrasaction(final int currentPage) {
        illustration.animate().translationX(-illustration.getWidth()).alpha(0).setDuration(timeFade).start();
        button_next.animate().alpha(0).setDuration(timeFade).withEndAction(new Runnable() {
            @Override
            public void run() {
                updatePage(currentPage);
                illustration.setTranslationX(illustration.getWidth());
                illustration.animate().translationX(0).alpha(1).setDuration(timeFade).start();
                button_next.animate().alpha(1).setDuration(timeFade).start();
            }
        }).start();
    }
    private void updatePage(int currentPage) {
        slider.setImageResource(imageResourceSlider.get(currentPage));
        illustration.setImageResource(imageResourceIllustration.get(currentPage));
        button_next.setImageResource(imageResourceButton.get(currentPage));
    }
}
