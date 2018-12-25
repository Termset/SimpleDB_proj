package simpledb;

/**
 * Tuple maintains information about the contents of a tuple.
 * Tuples have a specified schema specified by a TupleDesc object and contain
 * Field objects with the data for each field.
 */
public class Tuple {
    private Field[] fields;  //Field objects

    private TupleDesc tupleDesc;  //TupleDesc object

    private RecordId recordId;  //RecordId


    /**
     * Create a new tuple with the specified schema (type).
     *
     * @param td the schema of this tuple. It must be a valid TupleDesc
     *           instance with at least one field.
     */
    public Tuple(TupleDesc td) {
        // some code goes here
        // ���캯������ʼ��һ��Ԫ��
        this.tupleDesc = td;
        // ��ʼ��Field����
        fields = new Field[td.numFields()];
    }

    /**
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here
        // ����Ԫ���ģʽtupleDesc
        return tupleDesc;
    }

    /**
     * @return The RecordId representing the location of this tuple on
     * disk. May be null.
     */
    public RecordId getRecordId() {
        // some code goes here
        // ����Ԫ��Ĵ洢λ��recordId
        return recordId;
    }

    /**
     * Set the RecordId information for this tuple.
     *
     * @param rid the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        // some code goes here
        // ��ʼ��rid
        this.recordId = rid;
    }

    /**
     * Change the value of the ith field of this tuple.
     *
     * @param i index of the field to change. It must be a valid index.
     * @param f new value for the field.
     */

    public void setField(int i, Field f) {
        // some code goes here
        // ���õ�i��Ԫ�������
        if (!isValidIndex(i)) {
            throw new IllegalArgumentException("����Խ��");
        }
        fields[i] = f;
    }

    /**
     * @param i field index to return. Must be a valid index.
     * @return the value of the ith field, or null if it has not been set.
     */
    public Field getField(int i) {
        // some code goes here
        // ���ص�i��fields����
        if (!isValidIndex(i)) {
            throw new IllegalArgumentException("����Խ��");
        }
        return fields[i];
    }

    private boolean isValidIndex(int index) {
        // �ж�fields�Ƿ�Խ��,����ֵΪ��
        return index >= 0 && index < fields.length;
    }

    /**
     * Returns the contents of this Tuple as a string.����Ԫ�������
     * Note that to pass the system tests, the format needs to be as
     * follows:
     * <p>
     * column1\tcolumn2\tcolumn3\t...\tcolumnN\n
     * <p>
     * where \t is any whitespace, except newline, and \n is a newline
     */
    public String toString() {
        // some code goes here
        //throw new UnsupportedOperationException("Implement this");
        StringBuffer rowString = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {
            if (i == fields.length - 1) {
                //��������һ��Field���ͽӻ��з�������ӿո�
                rowString.append(fields[i].toString() + "\n");
            } else {
                rowString.append(fields[i].toString() + "\t");
            }
        }
        return rowString.toString();
    }
}
