package sam.android.utils.demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sam.android.utils.adapter.CommonExpandableListAdapter;
import sam.anroid.utils.R;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CommonExpandableListAdapter<ChildData, GroupData> commonExpandableListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.listView);
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

      //添加测试数据
        addTestData();
    }


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



    private void addTestData() {
        for (int i = 0; i < 5; i++)
        {
            GroupData groupData = new GroupData();
            groupData.groupName = "分组-"+i;
            commonExpandableListAdapter.getGroupData().add(groupData);
        }
        for (int i = 0; i < commonExpandableListAdapter.getGroupCount(); i++) {
            List<ChildData>  temp = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                ChildData childData = new ChildData();
                childData.childName = "第"+i+"组内容-"+j;
                temp.add(childData);

            }
            commonExpandableListAdapter.getChildrenData().add(temp);
        }

        for (int i = 0; i < commonExpandableListAdapter.getGroupCount(); i++) {
            Log.i("test", commonExpandableListAdapter.getGroupData().get(i).groupName);
            for (int j = 0; j < 20; j++)
            {
               //Log.e("test", commonExpandableListAdapter.getChildrenData().get(i).get(j).childName);
           }
        }

        commonExpandableListAdapter.notifyDataSetChanged();
    }







}
