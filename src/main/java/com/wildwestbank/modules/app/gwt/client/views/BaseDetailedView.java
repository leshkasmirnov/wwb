/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.wildwestbank.modules.common.db.model.Identified;

/**
 * Базовый класс представления с детализацией.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public abstract class BaseDetailedView<M extends Identified> extends BaseView<M> {

	private BorderLayoutContainer borderLayoutContainer;
	private ContentPanel detailContentPanel;
	private final boolean enableDetalization;

	public BaseDetailedView() {
		super();
		enableDetalization = true;
	}

	public BaseDetailedView(boolean enableDetalization) {
		super();
		this.enableDetalization = enableDetalization;
	}

	private void clearAndHideDetail() {
		detailContentPanel.clear();
		borderLayoutContainer.collapse(LayoutRegion.SOUTH);

	}

	private void changeDetail(M selectedItem) {
		final IsWidget detailFor = getDetailFor(selectedItem);
		while (detailContentPanel.iterator().hasNext()) {
			Widget w = detailContentPanel.iterator().next();
			detailContentPanel.remove(w);
		}
		detailContentPanel.forceLayout();
		detailContentPanel.add(detailFor.asWidget());

		borderLayoutContainer.expand(LayoutRegion.SOUTH);
	}

	@Override
	protected Grid<M> createGrid() {
		Grid<M> grid = super.createGrid();
		if (enableDetalization) {
			grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<M>() {

				@Override
				public void onSelectionChanged(SelectionChangedEvent<M> event) {
					if (!event.getSelection().isEmpty()) {
						changeDetail(event.getSelection().get(0));
					} else {
						clearAndHideDetail();
					}
				}
			});
		}
		return grid;
	}

	@Override
	public Widget asWidget() {
		if (enableDetalization) {
			if (verticalLayoutContainer == null) {
				verticalLayoutContainer = new VerticalLayoutContainer();
				toolBar = createToolBar();
				grid = createGrid();

				borderLayoutContainer = new BorderLayoutContainer();
				detailContentPanel = new ContentPanel();

				BorderLayoutData detailData = new BorderLayoutData(300);
				detailData.setCollapsible(true);
				detailData.setCollapseMini(true);
				detailData.setSplit(true);
				detailData.setMargins(new Margins(5, 0, 0, 0));

				detailContentPanel.setHeadingHtml(getDetailHeaderText());

				borderLayoutContainer.setBorders(true);
				borderLayoutContainer.setCenterWidget(grid, new MarginData());
				borderLayoutContainer.setSouthWidget(detailContentPanel, detailData);
				borderLayoutContainer.collapse(LayoutRegion.SOUTH);

				if (toolBar != null) {
					verticalLayoutContainer.add(toolBar, new VerticalLayoutData(1, -1));
				}
				verticalLayoutContainer.add(borderLayoutContainer, new VerticalLayoutData(1, 1));

				verticalLayoutContainer.addAttachHandler(new Handler() {

					@Override
					public void onAttachOrDetach(AttachEvent event) {
						verticalLayoutContainer.onResize();

					}
				});
			}

			return verticalLayoutContainer;
		} else {
			return super.asWidget();
		}
	}

	/**
	 * Get detail for master record (if view detailed)
	 * 
	 * @param model
	 * @return
	 */
	protected abstract IsWidget getDetailFor(final M model);

	public abstract String getDetailHeaderText();

}
