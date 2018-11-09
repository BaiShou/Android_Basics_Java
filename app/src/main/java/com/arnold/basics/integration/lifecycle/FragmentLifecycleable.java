package com.arnold.basics.integration.lifecycle;

import android.support.v4.app.Fragment;

import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.FragmentEvent;

/**
 * @author：baisoo 创建时间：2018/11/9 14:34
 * 类描述：让 {@link Fragment} 实现此接口,即可正常使用 {@link RxLifecycle}
 * <p>
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface FragmentLifecycleable extends Lifecycleable<FragmentEvent> {
}
