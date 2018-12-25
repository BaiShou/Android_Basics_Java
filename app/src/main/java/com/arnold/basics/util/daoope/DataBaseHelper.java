package com.arnold.basics.util.daoope;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.arnold.basics.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * 创建人：ArnoldTian
 * 创建时间：2018/12/19 10:04
 * 类描述：数据库升级工具类
 *
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DataBaseHelper extends DaoMaster.OpenHelper {
    private Context context;

    public DataBaseHelper(Context context, String name) {
        super(context, name);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        DaoMaster.createAllTables(db, true);    //第二个参数是true表示数据库升级时对于已存在的表不需要重新创建
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
