package utils.Menu;

import Frames.InternalFrameDemo;
import classe.Scene;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import java.awt.datatransfer.Transferable;

/**
 * utils.Menu
 * Created by Theo on 04/01/2015 for Ide3DProject.
 */
public class DropListener {
    public static void Drop(final InternalFrameDemo inte) {
        inte.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent event) {
                try {
                    Transferable tr = event.getTransferable();
                    Scene color = (Scene) tr.getTransferData(TransferableDP.TransferableScene.colorFlavor);
                    if (event.isDataFlavorSupported(TransferableDP.TransferableScene.colorFlavor)) {
                        event.acceptDrop(DnDConstants.ACTION_COPY);
                        //this.panel.setBackground(color);
                        event.dropComplete(true);
                        return;
                    }
                }
                catch (  Exception de) {
                    de.printStackTrace();
                    event.rejectDrop();
                    inte.log("Erreur de drop :  "+de.getMessage());
                }

            }
        });
    }
}
