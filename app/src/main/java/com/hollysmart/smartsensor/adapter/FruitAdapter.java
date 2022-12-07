package com.hollysmart.smartsensor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hollysmart.smartsensor.R;
import com.hollysmart.smartsensor.bean.Fruit;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter <FruitAdapter.ViewHolder>{

    private List<Fruit> mFruitList;

    //FruitAdapter构造函数，用于把要展示的数据源传入，并赋予值给类的私有变量mFruitList
    public FruitAdapter(List<Fruit> fruitList){
        mFruitList =  fruitList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//onCreateViewHolder() 方法是用于创建ViewHolder 实例的， 我们在这个方法中将item_Fruit 布局加载进来， 然后创建一个ViewHolder 实例， 并把加载出来的布局传入到构造函数当中， 最后将ViewHolder 的实例返回。
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
//onBindViewHolder() 方法是用于对RecyclerView子项的数据进行赋值的， 会在每个子项被滚动到屏幕内的时候执行， 这里我们通过position 参数得到当前项的Fruit实例， 然后再将数据设置到ViewHolder 的ImageView和TextView当中即可。
        Fruit fruit = mFruitList.get(position);
        viewHolder.fruitImage.setImageResource(fruit.getFruitImageID());
        viewHolder.fruitName.setText(fruit.getFruitName());
    }

    @Override
    public int getItemCount() {
//getItemCount() 方法就非常简单了， 它用于告诉RecyclerView一共有多少子项， 直接返回数据源的长度就可以了。
        return mFruitList.size();
    }

    //定义内部类ViewHolder，并继承RecyclerView.ViewHolder传入View参数通常是Recycler View子项的最外层布局
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }
}

