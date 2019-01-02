// Generated from Hello.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TYPE=1, STAT_IF=2, STAT_WHILE=3, STAT_ELSE=4, BVAL=5, VAR=6, AVAL=7, ADD=8, 
		DIV=9, AND=10, GREATER=11, ASSIGN=12, CLOSE_INSTR=13, TAB=14, NEW_LINE=15, 
		SPACE=16, IGNORE_EXPR=17, OPEN_BRACKET=18, CLOSE_BRACKET=19, OPEN_PAR=20, 
		CLOSE_PAR=21, NOT=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"TYPE", "STAT_IF", "STAT_WHILE", "STAT_ELSE", "BVAL", "VAR", "AVAL", "ADD", 
		"DIV", "AND", "GREATER", "ASSIGN", "CLOSE_INSTR", "TAB", "NEW_LINE", "SPACE", 
		"IGNORE_EXPR", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PAR", "CLOSE_PAR", 
		"NOT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'int'", "'if'", "'while'", "'else'", null, null, null, "'+'", "'/'", 
		"'&&'", "'>'", "'='", "';'", "'\t'", "'\n'", "' '", "','", "'{'", "'}'", 
		"'('", "')'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TYPE", "STAT_IF", "STAT_WHILE", "STAT_ELSE", "BVAL", "VAR", "AVAL", 
		"ADD", "DIV", "AND", "GREATER", "ASSIGN", "CLOSE_INSTR", "TAB", "NEW_LINE", 
		"SPACE", "IGNORE_EXPR", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PAR", "CLOSE_PAR", 
		"NOT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public HelloLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30{\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\6\7N\n\7\r\7\16\7O\3\b\6\bS\n\b\r\b\16"+
		"\bT\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30\3\2\4\3\2c|\3\2\62;\2}\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\63\3\2\2\2\7\66\3\2\2\2\t<\3\2"+
		"\2\2\13J\3\2\2\2\rM\3\2\2\2\17R\3\2\2\2\21V\3\2\2\2\23X\3\2\2\2\25Z\3"+
		"\2\2\2\27]\3\2\2\2\31_\3\2\2\2\33a\3\2\2\2\35c\3\2\2\2\37g\3\2\2\2!k\3"+
		"\2\2\2#o\3\2\2\2%q\3\2\2\2\'s\3\2\2\2)u\3\2\2\2+w\3\2\2\2-y\3\2\2\2/\60"+
		"\7k\2\2\60\61\7p\2\2\61\62\7v\2\2\62\4\3\2\2\2\63\64\7k\2\2\64\65\7h\2"+
		"\2\65\6\3\2\2\2\66\67\7y\2\2\678\7j\2\289\7k\2\29:\7n\2\2:;\7g\2\2;\b"+
		"\3\2\2\2<=\7g\2\2=>\7n\2\2>?\7u\2\2?@\7g\2\2@\n\3\2\2\2AB\7v\2\2BC\7t"+
		"\2\2CD\7w\2\2DK\7g\2\2EF\7h\2\2FG\7c\2\2GH\7n\2\2HI\7u\2\2IK\7g\2\2JA"+
		"\3\2\2\2JE\3\2\2\2K\f\3\2\2\2LN\t\2\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2"+
		"OP\3\2\2\2P\16\3\2\2\2QS\t\3\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2"+
		"\2U\20\3\2\2\2VW\7-\2\2W\22\3\2\2\2XY\7\61\2\2Y\24\3\2\2\2Z[\7(\2\2[\\"+
		"\7(\2\2\\\26\3\2\2\2]^\7@\2\2^\30\3\2\2\2_`\7?\2\2`\32\3\2\2\2ab\7=\2"+
		"\2b\34\3\2\2\2cd\7\13\2\2de\3\2\2\2ef\b\17\2\2f\36\3\2\2\2gh\7\f\2\2h"+
		"i\3\2\2\2ij\b\20\2\2j \3\2\2\2kl\7\"\2\2lm\3\2\2\2mn\b\21\2\2n\"\3\2\2"+
		"\2op\7.\2\2p$\3\2\2\2qr\7}\2\2r&\3\2\2\2st\7\177\2\2t(\3\2\2\2uv\7*\2"+
		"\2v*\3\2\2\2wx\7+\2\2x,\3\2\2\2yz\7#\2\2z.\3\2\2\2\6\2JOT\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}