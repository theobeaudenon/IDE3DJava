package TransferableDP;

import classe.Scene;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.Transferable;
/**
 * TransferableDP
 * Created by Theo on 23/01/2015 for Ide3DProject.
 */
 public class TransferableScene implements Transferable {
    public static DataFlavor colorFlavor = new DataFlavor(Scene.class, "A Color Object");
    protected static DataFlavor[] supportedFlavors = { colorFlavor };
    Scene color;
    public TransferableScene(Scene color) {
        this.color = color;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(colorFlavor) || flavor.equals(DataFlavor.stringFlavor))
            return true;
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(colorFlavor))
            return color;
        else if (flavor.equals(DataFlavor.stringFlavor))
            return color.toString();
        else
            throw new UnsupportedFlavorException(flavor);
    }
}
