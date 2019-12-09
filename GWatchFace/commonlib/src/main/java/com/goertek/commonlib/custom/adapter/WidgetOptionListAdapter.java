package com.goertek.commonlib.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.goertek.commonlib.R;
import com.goertek.commonlib.custom.widget.WidgetOptionBean;
import com.goertek.commonlib.utils.LogUtil;

import java.util.ArrayList;

/**
 * 自定义选项列表适配器
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/23
 */
public class WidgetOptionListAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private static final String TAG = "WidgetOptionListAdapter";

    private Context mContext;

    private WidgetOptionListAdapter.OnItemClickListener mListener;

    private ArrayList<WidgetOptionBean> mWidgetOptionBeans;

    public WidgetOptionListAdapter(Context context, ArrayList<WidgetOptionBean> widgetOptionBeans, WidgetOptionListAdapter.OnItemClickListener itemClickListener) {
        StringBuilder builder = new StringBuilder();
        builder.append("WidgetOptionListAdapter() mWidgetOptionBeans.size=");
        builder.append(widgetOptionBeans.size());
        this.mContext = context;
        this.mWidgetOptionBeans = widgetOptionBeans;
        this.mListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return this.mWidgetOptionBeans.size() + 1 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position != 0 && position != this.mWidgetOptionBeans.size() + 1 ? (this.mWidgetOptionBeans.get(position - 1)).getType() : 2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof WidgetOptionListAdapter.ViewHolderTitle) {
            ((WidgetOptionListAdapter.ViewHolderTitle)viewHolder).text.setText((mWidgetOptionBeans.get(position - 1)).getContent());
        } else if (viewHolder instanceof WidgetOptionListAdapter.ViewHolderItem) {
            WidgetOptionListAdapter.ViewHolderItem holderItem = (WidgetOptionListAdapter.ViewHolderItem)viewHolder;
            TextView itemText = holderItem.text;
            ArrayList widgetOptionBeans = this.mWidgetOptionBeans;
            --position;
            itemText.setText(((WidgetOptionBean)widgetOptionBeans.get(position)).getContent());
            holderItem.icon.setImageDrawable((mWidgetOptionBeans.get(position)).getIcon());
            if ((mWidgetOptionBeans.get(position)).isSelected()) {
                holderItem.select.setVisibility(View.VISIBLE);
            } else {
                holderItem.select.setVisibility(View.INVISIBLE);
            }

            holderItem.rlContainer.setOnClickListener(this);
            holderItem.rlContainer.setTag(position);
        } else if (viewHolder instanceof WidgetOptionListAdapter.ViewHolderHeader) {
            WidgetOptionListAdapter.ViewHolderHeader holderHeader = (WidgetOptionListAdapter.ViewHolderHeader)viewHolder;
            if (position == 0) {
                holderHeader.tvSelect.setText(this.mContext.getString(R.string.title_select));
            } else {
                holderHeader.tvSelect.setText("");
            }
        } else {
            LogUtil.v("WidgetOptionListAdapter", "onBindViewHolder() unkown ViewHolder");
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.rl_widget_option_container) {
           mListener.onClick((Integer)view.getTag());
        }

    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new WidgetOptionListAdapter.ViewHolderTitle(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_widget_option_title, null));
        } else if (viewType == 1) {
            return new WidgetOptionListAdapter.ViewHolderItem(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_widget_option_item, null));
        } else {
            return viewType == 2 ? new WidgetOptionListAdapter.ViewHolderHeader(LayoutInflater.from(mContext).inflate(R.layout.header_footer_view, parent, false)) : null;
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {
        private TextView tvSelect;

        ViewHolderHeader(View view) {
            super(view);
            this.tvSelect = view.findViewById(R.id.tv_widget_option_select);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        private ImageView icon;
        private RelativeLayout rlContainer;
        private ImageView select;
        private TextView text;

        ViewHolderItem(View view) {
            super(view);
            this.rlContainer = view.findViewById(R.id.rl_widget_option_container);
            this.text = view.findViewById(R.id.tv_widget_option_item_content);
            this.icon = view.findViewById(R.id.iv_widget_option_item_icon);
            this.select = view.findViewById(R.id.iv_widget_option_item_select);
        }
    }

    class ViewHolderTitle extends RecyclerView.ViewHolder {
        private TextView text;

        ViewHolderTitle(View view) {
            super(view);
            this.text = view.findViewById(R.id.tv_widget_option_title);
        }
    }
}
