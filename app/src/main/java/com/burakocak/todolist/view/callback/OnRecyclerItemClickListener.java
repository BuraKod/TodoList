package com.burakocak.todolist.view.callback;


public interface OnRecyclerItemClickListener {

    /*
        TThis listener was created for the click operations
        for the buttons on the recyclerView.

    */

    void onDeleteClick(Object object);
    void onCompleteClick(Object object);
}
