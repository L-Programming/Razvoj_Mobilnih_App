// Generated code from Butter Knife. Do not modify!
package barcic.funpark.view;

import android.view.View;
import android.widget.ListView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import barcic.funpark.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReadFragment_ViewBinding implements Unbinder {
  private ReadFragment target;

  private View view7f080073;

  @UiThread
  public ReadFragment_ViewBinding(final ReadFragment target, View source) {
    this.target = target;

    View view;
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeContainer, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.lista, "field 'listView'", ListView.class);
    view = Utils.findRequiredView(source, R.id.fab, "method 'noviStroj'");
    view7f080073 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.noviStroj();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ReadFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeRefreshLayout = null;
    target.listView = null;

    view7f080073.setOnClickListener(null);
    view7f080073 = null;
  }
}
