# CommonExpandableListAdapterDemo

引入地址: compile 'com.github.sam474850601:fastutils:1.0.3'

万能ExpandableListAdapter适配器，只需要提供child,和group的layout资源
实例代码


    /**
     * 分组数据
     */
    class GroupData
    {
        String groupName;
    }

    /**
     * 孩子数据
     */
    class ChildData
    {
        String childName;
    }

        //设置适配器
    expandableListView.setAdapter(commonExpandableListAdapter = new CommonExpandableListAdapter<ChildData, GroupData>(this,R.layout.adapter_child, R.layout.adapter_group ) {
            @Override
            protected void getChildView(ViewHolder holder, int groupPositon, int childPositon, boolean isLastChild, ChildData data) {
                TextView textView = holder.getView(R.id.childtxt);//孩子名字
                textView.setText(data.childName);
                Log.e("test", data.childName);
            }

            @Override
            protected void getGroupView(ViewHolder holder, int groupPositon, boolean isExpanded, GroupData data) {
                TextView textView = holder.getView(R.id.grouptxt);//分组名字
                ImageView arrowImage = holder.getView(R.id.groupIcon);//分组箭头
                textView.setText(data.groupName);
                //根据分组是否展开设置自定义箭头方向
                arrowImage.setImageResource(isExpanded?R.drawable.ic_arrow_expanded :R.drawable.ic_arrow_uexpanded);

            }
        });
    expandableListView.setAdapter(commonExpandableListAdapter);
