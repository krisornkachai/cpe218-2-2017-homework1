    public class NodeInfo {
        public char data;
        public Node nn;
        public String infix;

        public NodeInfo(char data,Node nn,String infix) {
           this.data=data;
           this.nn=nn;
           this.infix=infix;
        }

        @Override
        public String toString() {
        	String s = String.valueOf(data);
            return s;
        }
        }

    