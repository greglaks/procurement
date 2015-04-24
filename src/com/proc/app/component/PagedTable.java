package com.proc.app.component;

import com.vaadin.data.Container;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

import java.util.ArrayList;
import java.util.List;

public class PagedTable extends Table {
    private static final long serialVersionUID = 6881455780158545828L;

    public interface PageChangeListener {
        public void pageChanged(PagedTableChangeEvent event);
    }

    public class PagedTableChangeEvent {

        final PagedTable table;

        public PagedTableChangeEvent(PagedTable table) {
            this.table = table;
        }

        public PagedTable getTable() {
            return table;
        }

        public int getCurrentPage() {
            return table.getCurrentPage();
        }

        public int getTotalAmountOfPages() {
            return table.getTotalAmountOfPages();
        }
    }

    private List<PageChangeListener> listeners = null;

    private PagedTableContainer container;




    public PagedTable() {
        this(null);
    }

    public PagedTable(String caption) {
        super(caption);
        setPageLength(10);
        addStyleName("pagedtable");
    }
    

    public VerticalLayout createControls() {
        Label itemsPerPageLabel = new Label("Page:");
    
        
        final TextField currentPageTextField = new TextField();
        currentPageTextField.setValue(String.valueOf(getCurrentPage()));
        currentPageTextField.setConverter(Integer.class);
        final IntegerRangeValidator validator = new IntegerRangeValidator("Wrong page number", 1, getTotalAmountOfPages());
        currentPageTextField.addValidator(validator);
        Label separatorLabel = new Label("of", ContentMode.HTML);
        final Label totalPagesLabel = new Label(
                String.valueOf(getTotalAmountOfPages()), ContentMode.HTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                if (currentPageTextField.isValid()
                        && currentPageTextField.getValue() != null) {
                    int page = Integer.valueOf(String
                            .valueOf(currentPageTextField.getValue()));
                    setCurrentPage(page);
                }
            }
        });
        currentPageTextField.setWidth("80px");
        currentPageTextField.setHeight("37px");
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        VerticalLayout controlBar = new VerticalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        final Button first = new Button("<<", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(ClickEvent event) {
                setCurrentPage(0);
            }
        });
        final Button previous = new Button("<", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(ClickEvent event) {
                previousPage();
            }
        });
        final Button next = new Button(">", new ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            public void buttonClick(ClickEvent event) {
                nextPage();
            }
        });
        final Button last = new Button(">>", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(ClickEvent event) {
                setCurrentPage(getTotalAmountOfPages());
            }
        });
        
        HorizontalLayout pageManagementChildLayout = new HorizontalLayout();

        pageManagement.addComponent(itemsPerPageLabel);
        
        pageManagementChildLayout.addComponent(first);
        pageManagementChildLayout.addComponent(previous);
        pageManagementChildLayout.addComponent(currentPageTextField);
        pageManagementChildLayout.addComponent(next);
        pageManagementChildLayout.addComponent(last);
        
        pageManagement.addComponent(pageManagementChildLayout);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        
       
        
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement,
                Alignment.TOP_LEFT);
        controlBar.setSpacing(true);
        controlBar.setWidth("100%");

        addListener(new PageChangeListener() {
            public void pageChanged(PagedTableChangeEvent event) {
                first.setEnabled(container.getStartIndex() > 0);
                previous.setEnabled(container.getStartIndex() > 0);
                next.setEnabled(container.getStartIndex() < container
                        .getRealSize() - getPageLength());
                last.setEnabled(container.getStartIndex() < container
                        .getRealSize() - getPageLength());
                currentPageTextField.setValue(String.valueOf(getCurrentPage()));
                totalPagesLabel.setValue(String.valueOf(getTotalAmountOfPages()));
                validator.setMaxValue(getTotalAmountOfPages());
            }
        });
        return controlBar;
    }

    @Override
    public Container.Indexed getContainerDataSource() {
        return container;
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)) {
            throw new IllegalArgumentException(
                    "PagedTable can only use containers that implement Container.Indexed");
        }
        PagedTableContainer pagedTableContainer = new PagedTableContainer(
                (Container.Indexed) newDataSource);
        pagedTableContainer.setPageLength(getPageLength());
        super.setContainerDataSource(pagedTableContainer);
        this.container = pagedTableContainer;
        firePagedChangedEvent();
    }

    private void setPageFirstIndex(int firstIndex) {
        if (container != null) {
            if (firstIndex <= 0) {
                firstIndex = 0;
            }
            if (firstIndex > container.getRealSize() - 1) {
                int size = container.getRealSize() - 1;
                int pages = 0;
                if (getPageLength() != 0) {
                    pages = (int) Math.floor(0.0 + size / getPageLength());
                }
                firstIndex = pages * getPageLength();
            }
            container.setStartIndex(firstIndex);
            setCurrentPageFirstItemIndex(firstIndex);
            containerItemSetChange(new Container.ItemSetChangeEvent() {
                private static final long serialVersionUID = -5083660879306951876L;

                public Container getContainer() {
                    return container;
                }
            });
            if (alwaysRecalculateColumnWidths) {
                for (Object columnId : container.getContainerPropertyIds()) {
                    setColumnWidth(columnId, -1);
                }
            }
            firePagedChangedEvent();
        }
    }

    private void firePagedChangedEvent() {
        if (listeners != null) {
            PagedTableChangeEvent event = new PagedTableChangeEvent(this);
            for (PageChangeListener listener : listeners) {
                listener.pageChanged(event);
            }
        }
    }

    @Override
    public void setPageLength(int pageLength) {
        if (pageLength >= 0 && getPageLength() != pageLength) {
            container.setPageLength(pageLength);
            super.setPageLength(pageLength);
            firePagedChangedEvent();
        }
    }

    public void nextPage() {
        setPageFirstIndex(container.getStartIndex() + getPageLength());
    }

    public void previousPage() {
        setPageFirstIndex(container.getStartIndex() - getPageLength());
    }

    public int getCurrentPage() {
        double pageLength = getPageLength();
        int page = (int) Math.floor((double) container.getStartIndex()
                / pageLength) + 1;
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public void setCurrentPage(int page) {
        int newIndex = (page - 1) * getPageLength();
        if (newIndex < 0) {
            newIndex = 0;
        }
        if (newIndex >= 0 && newIndex != container.getStartIndex()) {
            setPageFirstIndex(newIndex);
        }
    }

    public int getTotalAmountOfPages() {
        int size = container.getContainer().size();
        double pageLength = getPageLength();
        int pageCount = (int) Math.ceil(size / pageLength);
        if (pageCount < 1) {
            pageCount = 1;
        }
        return pageCount;
    }

    public void addListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.add(listener);
    }

    public void removeListener(PageChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<PageChangeListener>();
        }
        listeners.remove(listener);
    }

    public void setAlwaysRecalculateColumnWidths(
            boolean alwaysRecalculateColumnWidths) {
        this.alwaysRecalculateColumnWidths = alwaysRecalculateColumnWidths;
    }

}