package br.uern.aridus.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.sparql.algebra.Op;
import com.hp.hpl.jena.sparql.algebra.Transform;
import com.hp.hpl.jena.sparql.algebra.op.OpAssign;
import com.hp.hpl.jena.sparql.algebra.op.OpBGP;
import com.hp.hpl.jena.sparql.algebra.op.OpConditional;
import com.hp.hpl.jena.sparql.algebra.op.OpDatasetNames;
import com.hp.hpl.jena.sparql.algebra.op.OpDiff;
import com.hp.hpl.jena.sparql.algebra.op.OpDisjunction;
import com.hp.hpl.jena.sparql.algebra.op.OpDistinct;
import com.hp.hpl.jena.sparql.algebra.op.OpExt;
import com.hp.hpl.jena.sparql.algebra.op.OpExtend;
import com.hp.hpl.jena.sparql.algebra.op.OpFilter;
import com.hp.hpl.jena.sparql.algebra.op.OpGraph;
import com.hp.hpl.jena.sparql.algebra.op.OpGroup;
import com.hp.hpl.jena.sparql.algebra.op.OpJoin;
import com.hp.hpl.jena.sparql.algebra.op.OpLabel;
import com.hp.hpl.jena.sparql.algebra.op.OpLeftJoin;
import com.hp.hpl.jena.sparql.algebra.op.OpList;
import com.hp.hpl.jena.sparql.algebra.op.OpMinus;
import com.hp.hpl.jena.sparql.algebra.op.OpNull;
import com.hp.hpl.jena.sparql.algebra.op.OpOrder;
import com.hp.hpl.jena.sparql.algebra.op.OpPath;
import com.hp.hpl.jena.sparql.algebra.op.OpProcedure;
import com.hp.hpl.jena.sparql.algebra.op.OpProject;
import com.hp.hpl.jena.sparql.algebra.op.OpPropFunc;
import com.hp.hpl.jena.sparql.algebra.op.OpQuad;
import com.hp.hpl.jena.sparql.algebra.op.OpQuadPattern;
import com.hp.hpl.jena.sparql.algebra.op.OpReduced;
import com.hp.hpl.jena.sparql.algebra.op.OpSequence;
import com.hp.hpl.jena.sparql.algebra.op.OpService;
import com.hp.hpl.jena.sparql.algebra.op.OpSlice;
import com.hp.hpl.jena.sparql.algebra.op.OpTable;
import com.hp.hpl.jena.sparql.algebra.op.OpTopN;
import com.hp.hpl.jena.sparql.algebra.op.OpTriple;
import com.hp.hpl.jena.sparql.algebra.op.OpUnion;
import com.hp.hpl.jena.sparql.core.BasicPattern;
import com.hp.hpl.jena.sparql.core.TriplePath;

public class OpTransformer implements Transform {
	
	int count = 0;
	Map<String, String> mapping = null;
	Hashtable<String, Integer> types;
	static final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	static final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
	static final String owl = "http://www.w3.org/2002/07/owl#";
	List<String> dataTriplePattern;
	List<String> schemaTriplePattern;
	static final String rdftype = rdf + "type";
	static final String equivalentProperty = owl + "equivalentProperty";
	static final String equivalentClass = owl + "equivalentClass";
	static final String sameAs = owl + "sameAs";
	static final String disjointWith = owl + "disjointWith";
	static final String complementOf = owl + "complementOf";
	static final String unionOf = owl + "unionOf";
	static final String subPropertyOf = rdfs + "subPropertyOf";
	static final String subClassOf = rdfs + "subClassOf";

	private void initialization(){
		types = new Hashtable<String, Integer>();
		types.put("OpTable", 0);
		types.put("OpBGP", 1);
		types.put("OpTriple", 2);
		types.put("OpQuad", 3);
		types.put("OpPath", 4);
		types.put("OpDatasetNames", 5);
		types.put("OpQuadPattern", 6);
		types.put("OpNull", 7);
		types.put("OpExt", 8);
		types.put("OpFilter", 9);
		types.put("OpGraph", 10);
		types.put("OpService", 11);
		types.put("OpProcedure", 12);
		types.put("OpPropFunc", 13);
		types.put("OpLabel", 14);
		types.put("OpAssign", 15);
		types.put("OpExtend", 16);
		types.put("OpSequence", 17);
		types.put("OpDisjunction", 18);
		types.put("OpList", 19);
		types.put("OpOrder", 20);
		types.put("OpTopN", 21);
		types.put("OpProject", 22);
		types.put("OpDistinct", 23);
		types.put("OpReduced", 24);
		types.put("OpSlice", 25);
		types.put("OpGroup", 26);
		types.put("OpJoin", 27);
		types.put("OpLeftJoin", 28);
		types.put("OpDiff", 29);
		types.put("OpMinus", 30);
		types.put("OpUnion", 31);
		types.put("OpConditional", 32);

		dataTriplePattern = new ArrayList<String>(2);
		dataTriplePattern.add(rdftype);
		dataTriplePattern.add(sameAs);

		schemaTriplePattern = new ArrayList<String>(7);
		schemaTriplePattern.add(equivalentProperty);
		schemaTriplePattern.add(equivalentClass);
		schemaTriplePattern.add(subPropertyOf);
		schemaTriplePattern.add(subClassOf);
		schemaTriplePattern.add(disjointWith);
		schemaTriplePattern.add(complementOf);
		schemaTriplePattern.add(unionOf);
	}
	
	public OpTransformer(Map<String, String> mapping){
		this.mapping = mapping;
		initialization();
	}
	
	public OpTransformer() {
		initialization();
	}

	public Op transform(Op op) {
		String name = op.getClass().getSimpleName();
		int id = -1;
		if (types.containsKey(name))
			id = types.get(name);
		else
			System.out.println("Op Class not found!");

//		System.out.println(id + " # Is " + name);
//		System.out.println(op.toString());

		Op opReturn = null;
		switch (id) {
		case 0: // OpTable
			opReturn = transform((OpTable) op);
			break;
		case 1: // OpBGP
			opReturn = transform((OpBGP) op);
			break;
		case 2: // OpTriple
			opReturn = transform((OpTriple) op);
			break;
		case 3: // OpQuad
			opReturn = transform(op);
			break;
		case 4: // OpPath
			opReturn = transform(op);
			break;
		case 5: // OpDatasetNames
			opReturn = transform(op);
			break;
		case 6: // OpQuadPattern
			opReturn = transform(op);
			break;
		case 7: // OpNull
			opReturn = transform(op);
			break;
		case 8: // OpExt
			opReturn = transform(op);
			break;
		case 9: // OpFilter
			opReturn = transform((OpFilter) op, null);
			break;
		case 10: // OpGraph
			opReturn = transform((OpGraph) op, null);
			break;
		case 11: // OpService
			opReturn = transform(op);
			break;
		case 12: // OpProcedure
			opReturn = transform((OpProcedure) op, null);
			break;
		case 13: // OpPropFunc
			opReturn = transform((OpPropFunc) op, null);
			break;
		case 14: // OpLabel
			opReturn = transform((OpLabel) op, null);
			break;
		case 15: // OpAssign
			opReturn = transform((OpAssign) op, null);
			break;
		case 16: // OpExtend
			opReturn = transform((OpExtend) op, null);
			break;
		case 17: // OpSequence
			opReturn = transform((OpSequence) op, null);
			break;
		case 18: // OpDisjunction
			opReturn = transform((OpDisjunction) op, null);
			break;
		case 19: // OpList
			opReturn = transform((OpList) op, null);
			break;
		case 20: // OpOrder
			opReturn = transform((OpOrder) op, null);
			break;
		case 21: // OpTopN
			opReturn = transform((OpTopN) op, null);
			break;
		case 22: // OpProject
			opReturn = transform((OpProject) op, null);
			break;
		case 23: // OpDistinct
			opReturn = transform((OpDistinct) op, null);
			break;
		case 24: // OpReduced
			opReturn = transform((OpReduced) op, null);
			break;
		case 25: // OpSlice
			opReturn = transform((OpSlice) op, null);
			break;
		case 26: // OpGroup
			opReturn = transform((OpGroup) op, null);
			break;
		case 27: // OpJoin
			opReturn = transform((OpJoin) op, null, null);
			break;
		case 28: // OpLeftJoin
			opReturn = transform((OpLeftJoin) op, null, null);
			break;
		case 29: // OpDiff
			opReturn = transform((OpDiff) op, null, null);
			break;
		case 30: // OpMinus
			opReturn = transform((OpMinus) op, null, null);
			break;
		case 31: // OpUnion
			opReturn = transform((OpUnion) op, null, null);
			break;
		case 32: // OpConditional
			opReturn = transform((OpConditional) op, null, null);
			break;
		}
		return opReturn;
	}
	
	public void setMapping(Map<String, String> mapping){
		this.mapping = mapping;
	}
	
	public void setMapping(String[][] mapping){
		this.mapping = new HashMap<String, String>(mapping.length);
		int ih = 0;
		while (ih < mapping.length) {
			this.mapping.put(mapping[ih][0], mapping[ih][1]);
			ih++;
		}
	}
	
	public Map<String, String> getMapping(){
		return mapping;
	}
	
	private boolean uriFilterProperties(Node n) {

		// String uriNode = n.getURI();
		// System.out.println(uriNode);

		if (n.isURI()
				&& (dataTriplePattern.contains(n.getURI()) || schemaTriplePattern
						.contains(n.getURI())))
			return true;

		return false;
	}

	private boolean isRDFType(Node p) {
		if (p != null && p.isURI()) {
			if (p.getURI().equals(rdftype))
				return true;
		}
		return false;
	}

	public boolean isDataTriplePattern(Triple t) {
		boolean ret = false;

		if (t != null) {
			Node p = t.getPredicate();
			if (p.isURI() && dataTriplePattern.contains(p.getURI())) {
				// rdftype || sameAs
				if (isRDFType(t.getPredicate())
						&& !uriFilterProperties(t.getObject()))
					ret = true;
				else
					ret = false;
			}
		}
		return ret;
	}

	public boolean isSchemaTriplePattern(Triple t) {
		if (t != null) {
			Node p = t.getPredicate();
			if (p.isURI() && schemaTriplePattern.contains(p.getURI()))
				return true;
		}
		return false;
	}

	@Override
	public Op transform(OpTable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpBGP arg0) {

		BasicPattern bgp = arg0.getPattern();
		List<Triple> triples = bgp.getList();
		Iterator<Triple> it = triples.iterator();

		// Op op1;
		BasicPattern bgp2 = new BasicPattern();
		while (it.hasNext()) {
			Triple t = it.next();
			OpTriple tr = new OpTriple(t);
			tr = (OpTriple) this.transform(tr);
			bgp2.add(tr.getTriple());

		}
		OpBGP bgps = new OpBGP(bgp2);
		return bgps;
	}

	@Override
	public Op transform(OpTriple arg0) {
//		System.out.println("OpTriple ("+this.count + ") # "  + arg0.toString());

		Triple t = arg0.getTriple();
		// Ordem de reescrita
		Node[] tn = new Node[] { t.getPredicate(), t.getObject(),
				t.getSubject() };

		if (isDataTriplePattern(t) || tn[0].isVariable() || !isSchemaTriplePattern(t)) {
			// altera os três campos s,p,o
			for (int i = 0; i < 3; i++) {
				if (!tn[i].isVariable() && !tn[i].isLiteral() && !tn[i].isBlank()
						&& !uriFilterProperties(tn[i])) {
					
					String s = tn[i].getURI().toString();				
					if (mapping.containsKey(s)){
						tn[i] = Node.createURI(mapping.get(s));
					}
				}
			}

		} else { // if (isSchemaTriplePattern(t)) {
			// relax() ????
			// altera apenas s,o
			for (int i = 1; i < 3; i++) {
				if (!tn[i].isVariable() && !tn[i].isLiteral() && !tn[i].isBlank()
						&& !uriFilterProperties(tn[i])) {
					String s = tn[i].getURI().toString();
					if (mapping.containsKey(s)){
						tn[i] = Node.createURI(mapping.get(s));
					}
				}
			}
		}

		Triple t2 = new Triple(tn[2], tn[0], tn[1]);
		OpTriple tre = new OpTriple(t2);
//		System.out.println("OpTriple ("+this.count + ") # "  + tre.toString());
		this.count++;
		return tre;
	}

	public Op transform(OpQuad arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpPath arg0) {
		TriplePath triple = arg0.getTriplePath();
		Node s = triple.getSubject();
		Node p = triple.getPredicate();
		Node o = triple.getObject();
		if (!s.isVariable())
			System.out.println(s.getURI());
		if (!p.isVariable())
			System.out.println(p.getURI());
		if (!o.isVariable())
			System.out.println(o.getURI());
		return null;
	}

	@Override
	public Op transform(OpDatasetNames arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpQuadPattern arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpNull arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpExt arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpFilter arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return OpFilter.filterDirect(arg0.getExprs(), arg1);
	}

	@Override
	public Op transform(OpGraph arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpService arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpProcedure arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpPropFunc arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpLabel arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpAssign arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpExtend arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpSequence arg0, List<Op> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpDisjunction arg0, List<Op> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpList arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpOrder arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpOrder(arg1, arg0.getConditions());
	}

	public Op transform(OpTopN arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpTopN(arg1, arg0.getLimit(), arg0.getConditions());
	}

	@Override
	public Op transform(OpProject arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpProject(arg1, arg0.getVars());
	}

	@Override
	public Op transform(OpDistinct arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpDistinct(arg1);
	}

	@Override
	public Op transform(OpReduced arg0, Op arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpSlice arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpSlice(arg1, arg0.getStart(), arg0.getLength());
	}

	@Override
	public Op transform(OpGroup arg0, Op arg1) {
		arg1 = arg0.getSubOp();
		arg1 = this.transform(arg1);
		return new OpGroup(arg1, arg0.getGroupVars(), arg0.getAggregators());
	}

	@Override
	public Op transform(OpJoin arg0, Op arg1, Op arg2) {
		arg1 = arg0.getLeft();
		arg2 = arg0.getRight();
		arg1 = this.transform(arg1);
		arg2 = this.transform(arg2);
		return ((OpJoin) OpJoin.create(arg1, arg2));
	}

	@Override
	public Op transform(OpLeftJoin arg0, Op arg1, Op arg2) {
		arg1 = arg0.getLeft();
		arg2 = arg0.getRight();
		arg1 = this.transform(arg1);
		arg2 = this.transform(arg2);
		return ((OpLeftJoin) OpLeftJoin.create(arg1, arg2, arg0.getExprs()));	
	}

	@Override
	public Op transform(OpDiff arg0, Op arg1, Op arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpMinus arg0, Op arg1, Op arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Op transform(OpUnion arg0, Op arg1, Op arg2) {
		arg1 = arg0.getLeft();
		arg2 = arg0.getRight();
		arg1 = this.transform(arg1);
		arg2 = this.transform(arg2);
		return new OpUnion(arg1, arg2);
	}

	@Override
	public Op transform(OpConditional arg0, Op arg1, Op arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
