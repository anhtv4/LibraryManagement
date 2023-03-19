package fu.spr8.librarymanagement;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    //show notification
    public static void showToast(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
