# IMP Language Parser
Project composed of two parts, both written using Java:
1. Parser and interpreter in JFlex
2. Parser in ANTLR
Parser which reads a C-like languages, builds a AST for an IMP language and interprets the AST (including errors). For example we have this:
- input (C-like code)
```c
int i, j, k;
i = 0;
j = 12;
while (!(i > 7)) {
	if (j > 20) {
		j = j + k;
	} else {
		j = j + 1;
	}
	i = i + 1;
}
```
- the AST build by the parser
```xml
<MainNode>
	<SequenceNode>
		<AssignmentNode> =
			<VariableNode> i
			<IntNode> 0
		<SequenceNode>
			<AssignmentNode> =
				<VariableNode> j
				<IntNode> 12
			<WhileNode> while
				<BracketNode> ()
					<NotNode> !
						<BracketNode> ()
							<GreaterNode> >
								<VariableNode> i
								<IntNode> 7
				<BlockNode> {}
					<SequenceNode>
						<IfNode> if
							<BracketNode> ()
								<GreaterNode> >
									<VariableNode> j
									<IntNode> 20
							<BlockNode> {}
								<AssignmentNode> =
									<VariableNode> j
									<PlusNode> +
										<VariableNode> j
										<VariableNode> k
							<BlockNode> {}
								<AssignmentNode> =
									<VariableNode> j
									<PlusNode> +
										<VariableNode> j
										<IntNode> 1
						<AssignmentNode> =
							<VariableNode> i
							<PlusNode> +
								<VariableNode> i
								<IntNode> 1

```
### Types of nodes from AST
- `MainNode` - the root node of the AST and it has one child
- `IntNode` - a numerical value and it's a leaf node
- `BoolNode` - a boolean value and it's a leaf node
- `VarNode` - a string value, representing the name of a variable and it's a leaf node
- `PlusNode`
- `DivNode`
- `BracketNode`
- `AndNode`
- `GreaterNode`
- `NotNode`
- `IfNode`
- `WhileNode`
- `AssignmentNode`
- `BlockNode`
- `SequenceNode`

## JFlex parser and interpreter

## ANTLR parser
