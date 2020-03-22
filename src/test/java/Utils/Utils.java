package Utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Instant;

public class Utils {
    public static long getUnixTimestamp() {
        return Instant.now().getEpochSecond();
    }

    /**
     * Gets the system clipboard content
     *
     * @return
     */
    public static String getClipboardContents() {

        String result = null;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =
                (contents != null) &&
                        contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {

            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            } catch ( IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }

        return result;
    }

}
