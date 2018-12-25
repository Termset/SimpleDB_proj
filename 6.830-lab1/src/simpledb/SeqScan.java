package simpledb;

import java.util.*;

/**
 * SeqScan is an implementation of a sequential scan access method that reads
 * each tuple of a table in no particular order (e.g., as they are laid out on
 * disk).
 */
public class SeqScan implements DbIterator {

    /**
     * Creates a sequential scan over the specified table as a part of the
     * specified transaction.
     * SeqScan��˳��ɨ����ʷ�����һ��ʵ�֣��������ض�˳���ȡ���ÿ��Ԫ��
     *
     * @param tid        The transaction this scan is running as a part of.
     * ��ɨ����Ϊ����һ�������е�����
     * @param tableid    the table to scan.
     * //@param tab leAlias the alias of this table (needed by the parser);
     * the returned tupleDesc should have fields with name tableAlias.fieldName
     * (note: this class is not responsible for handling a case where tableAlias
     * or fieldName are null.  It shouldn't crash if they are, but the resulting
     * name can be null.fieldName, tableAlias.null, or null.null).
     */
    private TransactionId tid;
    private int tableid;
    private String tableAlias;  // the alias of this table (needed by the parser)

    // �̳�DbFileIterator�ӿ�,����each tuple
    private DbFileIterator tupleIterator;


    /**
     * sequential scan access method
     * reads each tuple of a table in no particular order
     * as a part of the specified transaction
     */
    public SeqScan(TransactionId tid, int tableid, String tableAlias) {
        // some code goes here
        this.tid = tid;
        this.tableAlias = tableAlias;
        this.tableid = tableid;

        // �����Ķ���DbFile������
        tupleIterator = Database.getCatalog().getDbFile(tableid).iterator(tid);

    }

    public void open() throws DbException, TransactionAbortedException {
        // some code goes here
        // ��ʼ��������
        tupleIterator.open();
    }

    /**
     * Returns the TupleDesc with field names from the underlying HeapFile,
     * prefixed with the tableAlias string from the constructor.
     *
     * @return the TupleDesc with field names from the underlying HeapFile,
     * prefixed with the tableAlias string from the constructor.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here
        // ����TupleDesc
        TupleDesc tupleDesc = Database.getCatalog().getTupleDesc(tableid);
        int filedNum = tupleDesc.numFields();

        Type[] types = new Type[filedNum];  // ��������
        String[] names = new String[filedNum];

        //������ֵ
        for (int i = 0; i < filedNum; i++) {
            // Gets the type of the ith field of this TupleDesc.
            types[i] = tupleDesc.getType(i);
            // return the TupleDesc with field names from the underlying HeapFile
            // ��tableAlias stringΪǰ׺;tableAlias�Ǵ˱�ı���(��������Ҫ)
            names[i] = this.tableAlias + "." + tupleDesc.getFieldName(i);
        }

        // ����TupleDesc(Type[] typeAr, String[] fieldAr)
        return new TupleDesc(types, names);
    }

    public boolean hasNext() throws TransactionAbortedException, DbException {
        // some code goes here
        // ʵ��ͬһ��DbIterator�ӿڣ����������HeapFile���Ѿ�ʵ�ֵĺ���
        return tupleIterator.hasNext();
    }

    public Tuple next()
            throws NoSuchElementException, TransactionAbortedException, DbException {
        // some code goes here
        return tupleIterator.next();
    }

    public void close() {
        // some code goes here
        tupleIterator.close();
    }

    public void rewind()
            throws DbException, NoSuchElementException, TransactionAbortedException {
        // some code goes here
        tupleIterator.rewind();
    }
}
