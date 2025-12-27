package com.biotechnercp.handler.sample;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.biotechnercp.dialog.SampleDialog;
import com.biotechnercp.enumaration.SampleActionEnum;
import com.biotechnercp.model.Sample;
import com.biotechnercp.service.SampleService;
import com.biotechnercp.view.SampleView;

public class AddSampleHandler extends AbstractHandler {
	
	private final SampleService sampleService = new SampleService();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window =
                HandlerUtil.getActiveWorkbenchWindow(event);

        Sample sample = new Sample();
        SampleDialog dialog = new SampleDialog(window.getShell(), sample, SampleActionEnum.ADD);

        if (dialog.open() == Window.OK) {
        	try {
        		sampleService.saveSample(sample);
			} catch (Exception e) {
				MessageDialog.openError(window.getShell(), "Error", e.getMessage());
				return execute(event);
			}

            IWorkbenchPage page = window.getActivePage();
            SampleView view = (SampleView) page.findView(SampleView.ID);

            if (view != null) {
                view.refresh();
            }
        }
        return null;
	}

}
