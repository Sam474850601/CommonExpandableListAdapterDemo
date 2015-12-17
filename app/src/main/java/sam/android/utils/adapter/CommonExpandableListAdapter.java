package sam.android.utils.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * 万能ExpandableListAdapter适配器
 */
public abstract class CommonExpandableListAdapter<T, K> extends BaseExpandableListAdapter {
    protected abstract void getChildView(ViewHolder holder, int groupPositon, int childPositon, boolean isLastChild, T data);
    protected abstract void getGroupView(ViewHolder holder, int groupPositon, boolean isExpanded, K data);
    private int childResource;
    private int groupResource;
    private List<List<T>> childrenData = new ArrayList<>();
    private List<K> groupData = new ArrayList<>();
    private Context context;
    public List<List<T>> getChildrenData() {
        return childrenData;
    }

    public List<K> getGroupData() {
        return groupData;
    }

    public CommonExpandableListAdapter(Context context, int childResource, int groupResource) {
        this.context = context;
        this.childResource = childResource;
        this.groupResource = groupResource;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {

            convertView = LayoutInflater.from(context).inflate(childResource, parent,
                    false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        getChildView(viewHolder, groupPosition, childPosition, isLastChild, childrenData.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenData.size() > 0 ? childrenData.get(groupPosition).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {

            convertView = LayoutInflater.from(context).inflate(groupResource, parent,
                    false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        getGroupView(viewHolder, groupPosition, isExpanded, groupData.get(groupPosition));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public final static class ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        /**
         * 根据id获取view
         */
        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (null == view) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }
    }


}