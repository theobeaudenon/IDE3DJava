package utils.Menu;

import Frames.InternalFrameDemo;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

/**
 * utils.Menu
 * Created by Theo on 04/01/2015 for Ide3DProject.
 */
public class DropListener {
    public static void Drop(final InternalFrameDemo inte) {
        inte.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent event) {
                try {
                    Transferable tr=event.getTransferable();
                    //Forme obj=(Forme)tr.getTransferData();

                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    event.dropComplete(true);
                    inte.log(tr.getTransferData(DataFlavor.javaFileListFlavor).toString());
                }
                catch (  Exception e) {
                    e.printStackTrace();
                    event.rejectDrop();
                    inte.log("Erreur de drop :  "+e.getMessage());
                }

            }
        });
    }
}
