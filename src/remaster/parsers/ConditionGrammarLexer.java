// Generated from ConditionGrammar.g4 by ANTLR 4.7.2
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
public class ConditionGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, SUM=5, MIN=6, MAX=7, ADD=8, SUB=9, MUL=10, 
		DIV=11, MOD=12, AND=13, OR=14, EQ=15, GT=16, LT=17, GE=18, LE=19, NE=20, 
		ZERO=21, NZINT=22, NNFLOAT=23, IDENT=24, COMMENT_SINGLELINE=25, COMMENT_MULTILINE=26, 
		WHITESPACE=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "SUM", "MIN", "MAX", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "AND", "OR", "EQ", "GT", "LT", "GE", "LE", "NE", "ZERO", 
			"NZINT", "NNFLOAT", "D", "NZD", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", 
			"WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'sum'", "'min'", "'max'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'&&'", "'||'", "'=='", "'>'", "'<'", "'>='", "'<='", 
			"'!='", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "SUM", "MIN", "MAX", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "AND", "OR", "EQ", "GT", "LT", "GE", "LE", "NE", "ZERO", 
			"NZINT", "NNFLOAT", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", 
			"WHITESPACE"
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


	public ConditionGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ConditionGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00bd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\7\27v\n\27\f\27\16\27y\13\27\3\30\3\30\5"+
		"\30}\n\30\3\30\3\30\7\30\u0081\n\30\f\30\16\30\u0084\13\30\3\30\3\30\5"+
		"\30\u0088\n\30\3\30\6\30\u008b\n\30\r\30\16\30\u008c\5\30\u008f\n\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\7\33\u0097\n\33\f\33\16\33\u009a\13\33\3"+
		"\34\3\34\3\34\3\34\7\34\u00a0\n\34\f\34\16\34\u00a3\13\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\7\35\u00ad\n\35\f\35\16\35\u00b0\13\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\36\6\36\u00b8\n\36\r\36\16\36\u00b9\3\36\3\36"+
		"\4\u00a1\u00ae\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\2\63\2\65"+
		"\32\67\339\34;\35\3\2\b\4\2GGgg\3\2\62;\3\2\63;\5\2C\\aac|\6\2\62;C\\"+
		"aac|\5\2\13\f\17\17\"\"\2\u00c4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2"+
		"\2;\3\2\2\2\3=\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tC\3\2\2\2\13E\3\2\2\2\r"+
		"I\3\2\2\2\17M\3\2\2\2\21Q\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27W\3\2\2\2"+
		"\31Y\3\2\2\2\33[\3\2\2\2\35^\3\2\2\2\37a\3\2\2\2!d\3\2\2\2#f\3\2\2\2%"+
		"h\3\2\2\2\'k\3\2\2\2)n\3\2\2\2+q\3\2\2\2-s\3\2\2\2/|\3\2\2\2\61\u0090"+
		"\3\2\2\2\63\u0092\3\2\2\2\65\u0094\3\2\2\2\67\u009b\3\2\2\29\u00a8\3\2"+
		"\2\2;\u00b7\3\2\2\2=>\7*\2\2>\4\3\2\2\2?@\7+\2\2@\6\3\2\2\2AB\7]\2\2B"+
		"\b\3\2\2\2CD\7_\2\2D\n\3\2\2\2EF\7u\2\2FG\7w\2\2GH\7o\2\2H\f\3\2\2\2I"+
		"J\7o\2\2JK\7k\2\2KL\7p\2\2L\16\3\2\2\2MN\7o\2\2NO\7c\2\2OP\7z\2\2P\20"+
		"\3\2\2\2QR\7-\2\2R\22\3\2\2\2ST\7/\2\2T\24\3\2\2\2UV\7,\2\2V\26\3\2\2"+
		"\2WX\7\61\2\2X\30\3\2\2\2YZ\7\'\2\2Z\32\3\2\2\2[\\\7(\2\2\\]\7(\2\2]\34"+
		"\3\2\2\2^_\7~\2\2_`\7~\2\2`\36\3\2\2\2ab\7?\2\2bc\7?\2\2c \3\2\2\2de\7"+
		"@\2\2e\"\3\2\2\2fg\7>\2\2g$\3\2\2\2hi\7@\2\2ij\7?\2\2j&\3\2\2\2kl\7>\2"+
		"\2lm\7?\2\2m(\3\2\2\2no\7#\2\2op\7?\2\2p*\3\2\2\2qr\7\62\2\2r,\3\2\2\2"+
		"sw\5\63\32\2tv\5\61\31\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x.\3\2"+
		"\2\2yw\3\2\2\2z}\7\62\2\2{}\5-\27\2|z\3\2\2\2|{\3\2\2\2}~\3\2\2\2~\u0082"+
		"\7\60\2\2\177\u0081\5\61\31\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u008e\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0085\u0087\t\2\2\2\u0086\u0088\7/\2\2\u0087\u0086\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u008b\5\61\31\2\u008a\u0089\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008f\3\2\2\2\u008e\u0085\3\2\2\2\u008e\u008f\3\2\2\2\u008f\60\3\2\2"+
		"\2\u0090\u0091\t\3\2\2\u0091\62\3\2\2\2\u0092\u0093\t\4\2\2\u0093\64\3"+
		"\2\2\2\u0094\u0098\t\5\2\2\u0095\u0097\t\6\2\2\u0096\u0095\3\2\2\2\u0097"+
		"\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\66\3\2\2"+
		"\2\u009a\u0098\3\2\2\2\u009b\u009c\7\61\2\2\u009c\u009d\7\61\2\2\u009d"+
		"\u00a1\3\2\2\2\u009e\u00a0\13\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3"+
		"\2\2\2\u00a1\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a5\7\f\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\34"+
		"\2\2\u00a78\3\2\2\2\u00a8\u00a9\7\61\2\2\u00a9\u00aa\7,\2\2\u00aa\u00ae"+
		"\3\2\2\2\u00ab\u00ad\13\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2"+
		"\u00ae\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae"+
		"\3\2\2\2\u00b1\u00b2\7,\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\b\35\2\2\u00b5:\3\2\2\2\u00b6\u00b8\t\7\2\2\u00b7\u00b6\3\2\2\2"+
		"\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00bb\u00bc\b\36\2\2\u00bc<\3\2\2\2\r\2w|\u0082\u0087\u008c\u008e"+
		"\u0098\u00a1\u00ae\u00b9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}