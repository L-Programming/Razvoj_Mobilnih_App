// Generated code from Butter Knife. Do not modify!
package barcic.funpark.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import barcic.funpark.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CUDFragment_ViewBinding implements Unbinder {
  private CUDFragment target;

  private View view7f0800bb;

  @UiThread
  public CUDFragment_ViewBinding(final CUDFragment target, View source) {
    this.target = target;

    View view;
    target.nazivStroja = Utils.findRequiredViewAsType(source, R.id.nazivStroja, "field 'nazivStroja'", EditText.class);
    target.mjestoProizvodnje = Utils.findRequiredViewAsType(source, R.id.mjestoProizvodnje, "field 'mjestoProizvodnje'", EditText.class);
    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.godinaProizvodnje, "field 'dropDownMenu'", Spinner.class);
    target.slika = Utils.findRequiredViewAsType(source, R.id.slikaStroja, "field 'slika'", ImageView.class);
    target.noviStroj = Utils.findRequiredViewAsType(source, R.id.noviStroj, "field 'noviStroj'", Button.class);
    target.uslikaj = Utils.findRequiredViewAsType(source, R.id.uslikaj, "field 'uslikaj'", Button.class);
    target.promjenaStroja = Utils.findRequiredViewAsType(source, R.id.promjenaStroja, "field 'promjenaStroja'", Button.class);
    target.obrisiStroj = Utils.findRequiredViewAsType(source, R.id.obrisiStroj, "field 'obrisiStroj'", Button.class);
    view = Utils.findRequiredView(source, R.id.nazad, "method 'nazad'");
    view7f0800bb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.nazad();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CUDFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nazivStroja = null;
    target.mjestoProizvodnje = null;
    target.dropDownMenu = null;
    target.slika = null;
    target.noviStroj = null;
    target.uslikaj = null;
    target.promjenaStroja = null;
    target.obrisiStroj = null;

    view7f0800bb.setOnClickListener(null);
    view7f0800bb = null;
  }
}
