package business.com.businessapp.view.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 */
public interface ItemTouchHelperAdapter {

    /**
     * 移动
     */
    boolean onItemMove(int fromPosition, int toPosition);

}
