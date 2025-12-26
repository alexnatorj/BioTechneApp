package com.biotechnercp.view;

import java.time.LocalDate;
import java.util.function.Function;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.biotechnercp.model.Sample;
import com.biotechnercp.repository.SampleRepository;

public class SampleView extends ViewPart {
	public static final String ID = "BioTechneRPC.view";

	private TableViewer viewer;


	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		createToolbarButtons(parent);
		viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);

		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		createSampleColumns();
		viewer.setContentProvider(ArrayContentProvider.getInstance());


		viewer.setInput(SampleRepository.getInstance().findAll());
		getSite().setSelectionProvider(viewer);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private void createSampleColumns() {
		createColumn("Name", 150, Sample::getName);
		createColumn("Date", 120, sample -> {
			LocalDate date = sample.getDate();
			return date != null ? date.toString() : "";
		});
		createColumn("Description", 300, Sample::getDescription);
	}

	private void createColumn(String title, int width, Function<Sample, String> valueProvider) {

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);

		TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(width);
		column.setResizable(true);
		column.setMoveable(true);

		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return valueProvider.apply((Sample) element);
			}
		});
	}
	
	private void createToolbarButtons(Composite parent) {
		ToolBar toolbar = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
	    toolbar.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
	    
	    toolbar.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
	    
	    
	    createToolItem(toolbar, "Add Sample", "com.biotechnercp.command.addSample");
	    createToolItem(toolbar, "Edit Sample", "com.biotechnercp.command.editSample");
	    createToolItem(toolbar, "Delete Sample", "com.biotechnercp.command.deleteSample");
	}
	
	private ToolItem createToolItem(ToolBar bar, String text, String commandId) {
		ToolItem item = new ToolItem(bar, SWT.PUSH);
	    item.setText(text);
	    item.addListener(SWT.Selection, e -> {
	        IHandlerService handlerService =
	            SampleView.this.getSite().getService(IHandlerService.class);

	        try {
	            handlerService.executeCommand(commandId, null);
	        } catch (Exception ex) {
	            throw new RuntimeException(ex);
	        }
	    });
	    return item;
	}
	
	public void refresh() {
		if (viewer != null && !viewer.getControl().isDisposed()) {
	        viewer.refresh();
	    }
	}

}