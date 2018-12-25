package com.arnold.basics.util.daoope;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.arnold.basics.dao.DaoMaster;
import com.arnold.basics.dao.DaoSession;


/**
 * 创建人：tzh-t121
 * 创建时间：2017/3/6 14:57
 * 类描述：数据库管理类
 * <p>
 * <p>
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DbManager {

    private static DbManager mDbManager;
    private static final String DB_NAME = "measure.db";
//    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DataBaseHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private DbManager(Context context) {
        // 初始化数据库信息
        mDevOpenHelper = new DataBaseHelper(context.getApplicationContext(), DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);

    }

    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }


    /**
     * 获取可写数据库
     *
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getWritableDatabase();
    }


    /**
     * 获取DaoMaster
     *
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     *
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }

}
