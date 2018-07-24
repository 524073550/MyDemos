package business.com.businessapp.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import java.util.List;

/**
 * 带点击事件的RecyclerView 适配器
 * xiejingwen
 */
public abstract class RecyclerAdapter<VH extends ViewHolder> extends RecyclerView.Adapter<VH> {
    OnItemClick onItemClick;
    OnLongItemClick onLongItemClick;

    public void setOnItemClickListener(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }
    public void setonLongItemClickListener(OnLongItemClick onLongItemClick){
        this.onLongItemClick=onLongItemClick;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (onItemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(v,position);
                }
            });

        }
        if (onLongItemClick!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongItemClick.onLongItemClick(v,position);
                    return true;
                }
            });
        }
    }

}
