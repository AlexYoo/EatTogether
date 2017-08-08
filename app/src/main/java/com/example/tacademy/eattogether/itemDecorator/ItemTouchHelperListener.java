package com.example.tacademy.eattogether.itemDecorator;

/**
 * Created by Tacademy on 2017-08-08.
 */

public interface ItemTouchHelperListener {
    boolean onItemMoved(int fromPosition, int toPosition);
    void onItemRemove(int position);
}
