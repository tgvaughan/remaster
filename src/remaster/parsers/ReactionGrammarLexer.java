// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.10.1
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
public class ReactionGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, ZERO=6, NZINT=7, NNFLOAT=8, IDENT=9, 
		COMMENT_SINGLELINE=10, COMMENT_MULTILINE=11, WHITESPACE=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "ZERO", "NZINT", "NNFLOAT", "D", 
			"NZD", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'+'", "':'", "'['", "']'", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ZERO", "NZINT", "NNFLOAT", "IDENT", 
			"COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
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


	public ReactionGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ReactionGrammar.g4"; }

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
		"\u0004\u0000\ft\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0005\u0006-\b\u0006\n\u0006\f\u00060\t\u0006\u0001\u0007\u0001"+
		"\u0007\u0003\u00074\b\u0007\u0001\u0007\u0001\u0007\u0005\u00078\b\u0007"+
		"\n\u0007\f\u0007;\t\u0007\u0001\u0007\u0001\u0007\u0003\u0007?\b\u0007"+
		"\u0001\u0007\u0004\u0007B\b\u0007\u000b\u0007\f\u0007C\u0003\u0007F\b"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0005\nN\b\n\n"+
		"\n\f\nQ\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"W\b\u000b\n\u000b\f\u000bZ\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005\fd\b\f\n\f\f\fg\t\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0004\ro\b\r\u000b\r"+
		"\f\rp\u0001\r\u0001\r\u0002Xe\u0000\u000e\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\u0000\u0013"+
		"\u0000\u0015\t\u0017\n\u0019\u000b\u001b\f\u0001\u0000\u0006\u0002\u0000"+
		"EEee\u0001\u000009\u0001\u000019\u0003\u0000AZ__az\u0004\u000009AZ__a"+
		"z\u0003\u0000\t\n\r\r  {\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000\u0003 \u0001\u0000\u0000"+
		"\u0000\u0005\"\u0001\u0000\u0000\u0000\u0007$\u0001\u0000\u0000\u0000"+
		"\t&\u0001\u0000\u0000\u0000\u000b(\u0001\u0000\u0000\u0000\r*\u0001\u0000"+
		"\u0000\u0000\u000f3\u0001\u0000\u0000\u0000\u0011G\u0001\u0000\u0000\u0000"+
		"\u0013I\u0001\u0000\u0000\u0000\u0015K\u0001\u0000\u0000\u0000\u0017R"+
		"\u0001\u0000\u0000\u0000\u0019_\u0001\u0000\u0000\u0000\u001bn\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u0005-\u0000\u0000\u001e\u001f\u0005>\u0000\u0000"+
		"\u001f\u0002\u0001\u0000\u0000\u0000 !\u0005+\u0000\u0000!\u0004\u0001"+
		"\u0000\u0000\u0000\"#\u0005:\u0000\u0000#\u0006\u0001\u0000\u0000\u0000"+
		"$%\u0005[\u0000\u0000%\b\u0001\u0000\u0000\u0000&\'\u0005]\u0000\u0000"+
		"\'\n\u0001\u0000\u0000\u0000()\u00050\u0000\u0000)\f\u0001\u0000\u0000"+
		"\u0000*.\u0003\u0013\t\u0000+-\u0003\u0011\b\u0000,+\u0001\u0000\u0000"+
		"\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000./\u0001\u0000"+
		"\u0000\u0000/\u000e\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u0000"+
		"14\u00050\u0000\u000024\u0003\r\u0006\u000031\u0001\u0000\u0000\u0000"+
		"32\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000059\u0005.\u0000\u0000"+
		"68\u0003\u0011\b\u000076\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:E\u0001\u0000\u0000"+
		"\u0000;9\u0001\u0000\u0000\u0000<>\u0007\u0000\u0000\u0000=?\u0005-\u0000"+
		"\u0000>=\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@B\u0003\u0011\b\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DF\u0001"+
		"\u0000\u0000\u0000E<\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"F\u0010\u0001\u0000\u0000\u0000GH\u0007\u0001\u0000\u0000H\u0012\u0001"+
		"\u0000\u0000\u0000IJ\u0007\u0002\u0000\u0000J\u0014\u0001\u0000\u0000"+
		"\u0000KO\u0007\u0003\u0000\u0000LN\u0007\u0004\u0000\u0000ML\u0001\u0000"+
		"\u0000\u0000NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001"+
		"\u0000\u0000\u0000P\u0016\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000"+
		"\u0000RS\u0005/\u0000\u0000ST\u0005/\u0000\u0000TX\u0001\u0000\u0000\u0000"+
		"UW\t\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000Y[\u0001\u0000\u0000"+
		"\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005\n\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]^\u0006\u000b\u0000\u0000^\u0018\u0001\u0000\u0000\u0000"+
		"_`\u0005/\u0000\u0000`a\u0005*\u0000\u0000ae\u0001\u0000\u0000\u0000b"+
		"d\t\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000"+
		"ef\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000"+
		"\u0000ge\u0001\u0000\u0000\u0000hi\u0005*\u0000\u0000ij\u0005/\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000kl\u0006\f\u0000\u0000l\u001a\u0001\u0000\u0000"+
		"\u0000mo\u0007\u0005\u0000\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000"+
		"\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0001"+
		"\u0000\u0000\u0000rs\u0006\r\u0000\u0000s\u001c\u0001\u0000\u0000\u0000"+
		"\u000b\u0000.39>CEOXep\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}