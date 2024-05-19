activity写完布局文件

```
    <androidx.viewpager.widget.ViewPager
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:id="@+id/vp"/>
    
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/bottom_item_menu"
        app:menu="@menu/bottom_item"
/>
</Linear
```

item来自于menu文件夹下的 文件

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/menu_home"
        android:icon="@drawable/selecthome"
        android:title="首页"/>
    <item
        android:id="@+id/menu_find"
        android:icon="@drawable/selectfind"
        android:title="搜索"/>
    <item
        android:id="@+id/menu_mine"
        android:icon="@drawable/selectmine"
        android:title="我的"/>

</menu>
```



![image-20240519123358107](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20240519123358107.png)一个是主菜单，一个是选项中的菜单，所以需要创建两个fragmen去分别填充

首先是 底部的 填充，创建ExampleFragment 填充使用，

```
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TextView tvContent=view.findViewById(R.id.f_content);
    if (!TextUtils.isEmpty(mParam1)){
        tvContent.setText(mParam1);
    }
}
```

子菜单的填充 VPHomeFragmen

先不写具体的，回到initdate中填充

```
private void initData() {
    mfragmentList=new ArrayList<>();
    VPHomeFragment vpHomeFragmen = VPHomeFragment.newInstance("首页", "-");
    mfragmentList.add(vpHomeFragmen);
    ExampleFragment findFragment = ExampleFragment.newInstance("搜素", "-");
    ExampleFragment mineFragment = ExampleFragment.newInstance("我的", "-");
    mfragmentList.add(findFragment);
    mfragmentList.add(mineFragment);
}
```

然后给viewpager设置适配器，	

##### 运行，此时可以滑动三个页面了切换

底部按钮点击不会切换  需要使用ViewPager联动底部导航按钮，

// 为ViewPager添加页面切换监听
 mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

```
//页面切换的处理
private void onPageChange(int position) {
    switch (position){
        case 0: mbottomNavigationView.setSelectedItemId(R.id.menu_home); break;
        case 1: mbottomNavigationView.setSelectedItemId(R.id.menu_find);break;
        case 2: mbottomNavigationView.setSelectedItemId(R.id.menu_mine);break;
        default:break;
    }
}
```

然后给BottomNavigationView设置点击事件

```
mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        //根据不同的id改变我们的viewpager  R.id.menu_home使用不了swic 换成if else
        if (itemId == R.id.menu_home) {
            mviewPager.setCurrentItem(0);
        } else if (itemId == R.id.menu_find) {
            mviewPager.setCurrentItem(1);
        } else if (itemId == R.id.menu_mine) {
            mviewPager.setCurrentItem(2);
        }
        return true;
    }
});
```

ViewPager和BottomNavigationView关联完成； 

##### 此时底部按钮点击可以切换 ，但是按钮颜色未变化 下面是homeFragmen的编写

使用tabLayout

![image-20240519144718696](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20240519144718696.png)

里面又是一个viewpager

然后initdata

这个页面还有标题 需要创建一个新的adapter'

这个tabLaout 和viewPager 的交互就没有那么麻烦了，只需要一行代码

```
mtabLayout.setupWithViewPager(mviewPager);
```