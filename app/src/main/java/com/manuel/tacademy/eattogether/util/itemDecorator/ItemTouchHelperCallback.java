package com.manuel.tacademy.eattogether.util.itemDecorator;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.manuel.tacademy.eattogether.util.S;

/**
 * Created by Tacademy on 2017-08-08.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback{

    ItemTouchHelperListener itemTouchHelperListener;

    public ItemTouchHelperCallback(ItemTouchHelperListener itemTouchHelperListener) {
        this.itemTouchHelperListener = itemTouchHelperListener;
    }

    //각 View에서 어떤 user action이 가능한지 정의
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return itemTouchHelperListener.onItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        S.getInstance().log("onSwiped");
        itemTouchHelperListener.onItemRemove(viewHolder.getAdapterPosition());
    }
}
