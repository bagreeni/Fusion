package com.callisto.fusion.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.*;


/**
 * Created by Maddiemay12 on 3/30/18.
 */

public class SwipeController extends Callback {

    boolean swipeBack;
    AppCompatActivity activity;

    public SwipeController(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Intent intent = new Intent(activity, MoreActivity.class);
      //  intent.putExtra("TASKID", viewHolder.itemView.get)
        activity.startActivity(intent);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
        if (actionState == ACTION_STATE_SWIPE){
            setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        Log.d("SWIPE", "dx: " + dX + ", dy: " + dY);
        super.onChildDraw(c, recyclerView, viewHolder, dX,dY, actionState,isCurrentlyActive);
    }

    private void setTouchListener(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent event){
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                return false;
            }
        });
    }

    public int convertToAbsoluteDirection(int flags, int layoutDirection){
        if(swipeBack){
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

}
