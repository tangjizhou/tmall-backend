package net.mshome.twisted.tmall.service.excel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/10/31
 */
public class ProductExportHandler implements SheetWriteHandler {

    private int rowSize;

    public ProductExportHandler(int rowSize) {
        this.rowSize = rowSize;

    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {


    }

}
