package com.biotechnercp.handler.sample;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.biotechnercp.dialog.SampleDialog;
import com.biotechnercp.model.Sample;
import com.biotechnercp.service.SampleService;
import com.biotechnercp.view.SampleView;

public class EditSampleHandler extends AbstractHandler {

	private final SampleService sampleService = new SampleService();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ISelection selection =
			    window.getSelectionService().getSelection();
		if (selection.isEmpty()) {
			MessageDialog.openWarning( window.getShell(),
	                "Warning Delete",
	                "No samples selected");
			return null;
		}
		if (selection instanceof IStructuredSelection) {
		    Sample sample =
		        (Sample) ((IStructuredSelection) selection)
		            .getFirstElement();

		    SampleDialog dialog =
		        new SampleDialog(window.getShell(), sample);

		    if (dialog.open() == Window.OK) {
		    	try {
	        		sampleService.updateSample(sample);
				} catch (Exception e) {
					MessageDialog.openError(window.getShell(), "Error", e.getMessage());
					return execute(event);
				}
		    	IWorkbenchPage page = window.getActivePage();
	            SampleView view = (SampleView) page.findView(SampleView.ID);
		        view.refresh();
		    }
		}

		return null;
	}

}
