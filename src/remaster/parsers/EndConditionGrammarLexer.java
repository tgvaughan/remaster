// Generated from EndConditionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EndConditionGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, SUM=5, MIN=6, MAX=7, AND=8, OR=9, EQ=10, 
		GT=11, LT=12, GE=13, LE=14, NE=15, ZERO=16, NZINT=17, NNFLOAT=18, IDENT=19, 
		COMMENT_SINGLELINE=20, COMMENT_MULTILINE=21, WHITESPACE=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "SUM", "MIN", "MAX", "AND", "OR", "EQ", 
			"GT", "LT", "GE", "LE", "NE", "ZERO", "NZINT", "NNFLOAT", "D", "NZD", 
			"IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'sum'", "'min'", "'max'", "'&&'", 
			"'||'", "'=='", "'>'", "'<'", "'>='", "'<='", "'!='", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "SUM", "MIN", "MAX", "AND", "OR", "EQ", 
			"GT", "LT", "GE", "LE", "NE", "ZERO", "NZINT", "NNFLOAT", "IDENT", "COMMENT_SINGLELINE", 
			"COMMENT_MULTILINE", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public EndConditionGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EndConditionGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00a9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\7"+
		"\22b\n\22\f\22\16\22e\13\22\3\23\3\23\5\23i\n\23\3\23\3\23\7\23m\n\23"+
		"\f\23\16\23p\13\23\3\23\3\23\5\23t\n\23\3\23\6\23w\n\23\r\23\16\23x\5"+
		"\23{\n\23\3\24\3\24\3\25\3\25\3\26\3\26\7\26\u0083\n\26\f\26\16\26\u0086"+
		"\13\26\3\27\3\27\3\27\3\27\7\27\u008c\n\27\f\27\16\27\u008f\13\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u0099\n\30\f\30\16\30\u009c\13"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\6\31\u00a4\n\31\r\31\16\31\u00a5\3\31"+
		"\3\31\4\u008d\u009a\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\2)\2+\25-\26/\27\61\30\3\2"+
		"\b\4\2GGgg\3\2\62;\3\2\63;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\""+
		"\"\2\u00b0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r?\3\2"+
		"\2\2\17C\3\2\2\2\21G\3\2\2\2\23J\3\2\2\2\25M\3\2\2\2\27P\3\2\2\2\31R\3"+
		"\2\2\2\33T\3\2\2\2\35W\3\2\2\2\37Z\3\2\2\2!]\3\2\2\2#_\3\2\2\2%h\3\2\2"+
		"\2\'|\3\2\2\2)~\3\2\2\2+\u0080\3\2\2\2-\u0087\3\2\2\2/\u0094\3\2\2\2\61"+
		"\u00a3\3\2\2\2\63\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6\3\2\2\2\67"+
		"8\7]\2\28\b\3\2\2\29:\7_\2\2:\n\3\2\2\2;<\7u\2\2<=\7w\2\2=>\7o\2\2>\f"+
		"\3\2\2\2?@\7o\2\2@A\7k\2\2AB\7p\2\2B\16\3\2\2\2CD\7o\2\2DE\7c\2\2EF\7"+
		"z\2\2F\20\3\2\2\2GH\7(\2\2HI\7(\2\2I\22\3\2\2\2JK\7~\2\2KL\7~\2\2L\24"+
		"\3\2\2\2MN\7?\2\2NO\7?\2\2O\26\3\2\2\2PQ\7@\2\2Q\30\3\2\2\2RS\7>\2\2S"+
		"\32\3\2\2\2TU\7@\2\2UV\7?\2\2V\34\3\2\2\2WX\7>\2\2XY\7?\2\2Y\36\3\2\2"+
		"\2Z[\7#\2\2[\\\7?\2\2\\ \3\2\2\2]^\7\62\2\2^\"\3\2\2\2_c\5)\25\2`b\5\'"+
		"\24\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d$\3\2\2\2ec\3\2\2\2fi\7"+
		"\62\2\2gi\5#\22\2hf\3\2\2\2hg\3\2\2\2ij\3\2\2\2jn\7\60\2\2km\5\'\24\2"+
		"lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oz\3\2\2\2pn\3\2\2\2qs\t\2\2\2"+
		"rt\7/\2\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2uw\5\'\24\2vu\3\2\2\2wx\3\2\2\2"+
		"xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zq\3\2\2\2z{\3\2\2\2{&\3\2\2\2|}\t\3\2\2"+
		"}(\3\2\2\2~\177\t\4\2\2\177*\3\2\2\2\u0080\u0084\t\5\2\2\u0081\u0083\t"+
		"\6\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085,\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\7\61\2\2"+
		"\u0088\u0089\7\61\2\2\u0089\u008d\3\2\2\2\u008a\u008c\13\2\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7\f\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0093\b\27\2\2\u0093.\3\2\2\2\u0094\u0095\7\61\2\2\u0095\u0096"+
		"\7,\2\2\u0096\u009a\3\2\2\2\u0097\u0099\13\2\2\2\u0098\u0097\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009d\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7,\2\2\u009e\u009f\7\61\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\b\30\2\2\u00a1\60\3\2\2\2\u00a2\u00a4\t\7\2"+
		"\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\31\2\2\u00a8\62\3\2\2\2\r\2"+
		"chnsxz\u0084\u008d\u009a\u00a5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}