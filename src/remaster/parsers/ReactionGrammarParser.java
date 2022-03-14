// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.9.2
package remaster.parsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReactionGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ADD=13, SUB=14, MUL=15, DIV=16, MOD=17, POW=18, 
		EXP=19, LOG=20, SQRT=21, SUM=22, THETA=23, ABS=24, AND=25, OR=26, EQ=27, 
		GT=28, LT=29, GE=30, LE=31, NE=32, ZERO=33, NZINT=34, NNFLOAT=35, IDENT=36, 
		COMMENT_SINGLELINE=37, COMMENT_MULTILINE=38, WHITESPACE=39;
	public static final int
		RULE_reaction = 0, RULE_reactants = 1, RULE_products = 2, RULE_popsum = 3, 
		RULE_popel = 4, RULE_factor = 5, RULE_id = 6, RULE_assignment = 7, RULE_popname = 8, 
		RULE_loc = 9, RULE_locidx = 10, RULE_expression = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"reaction", "reactants", "products", "popsum", "popel", "factor", "id", 
			"assignment", "popname", "loc", "locidx", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "':'", "':='", "'['", "']'", "'('", "')'", "'{'", "','", 
			"'}'", "'!'", "'?'", "'+'", "'-'", "'*'", "'/'", "'%'", "'^'", "'exp'", 
			"'log'", "'sqrt'", "'sum'", "'theta'", "'abs'", "'&&'", "'||'", "'=='", 
			"'>'", "'<'", "'>='", "'<='", "'!='", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "ADD", "SUB", "MUL", "DIV", "MOD", "POW", "EXP", "LOG", "SQRT", 
			"SUM", "THETA", "ABS", "AND", "OR", "EQ", "GT", "LT", "GE", "LE", "NE", 
			"ZERO", "NZINT", "NNFLOAT", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", 
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

	@Override
	public String getGrammarFileName() { return "ReactionGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReactionGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ReactionContext extends ParserRuleContext {
		public ReactantsContext reactants() {
			return getRuleContext(ReactantsContext.class,0);
		}
		public ProductsContext products() {
			return getRuleContext(ProductsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ReactionGrammarParser.EOF, 0); }
		public ReactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterReaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitReaction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitReaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactionContext reaction() throws RecognitionException {
		ReactionContext _localctx = new ReactionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			reactants();
			setState(25);
			match(T__0);
			setState(26);
			products();
			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactantsContext extends ParserRuleContext {
		public PopsumContext popsum() {
			return getRuleContext(PopsumContext.class,0);
		}
		public ReactantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterReactants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitReactants(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitReactants(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactantsContext reactants() throws RecognitionException {
		ReactantsContext _localctx = new ReactantsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reactants);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			popsum();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProductsContext extends ParserRuleContext {
		public PopsumContext popsum() {
			return getRuleContext(PopsumContext.class,0);
		}
		public ProductsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_products; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterProducts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitProducts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitProducts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductsContext products() throws RecognitionException {
		ProductsContext _localctx = new ProductsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_products);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			popsum();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopsumContext extends ParserRuleContext {
		public List<PopelContext> popel() {
			return getRuleContexts(PopelContext.class);
		}
		public PopelContext popel(int i) {
			return getRuleContext(PopelContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(ReactionGrammarParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(ReactionGrammarParser.ADD, i);
		}
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public PopsumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popsum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopsum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopsum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopsum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopsumContext popsum() throws RecognitionException {
		PopsumContext _localctx = new PopsumContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_popsum);
		int _la;
		try {
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NZINT:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				popel();
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ADD) {
					{
					{
					setState(34);
					match(ADD);
					setState(35);
					popel();
					}
					}
					setState(40);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				match(ZERO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopelContext extends ParserRuleContext {
		public PopnameContext popname() {
			return getRuleContext(PopnameContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public LocContext loc() {
			return getRuleContext(LocContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public PopelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopelContext popel() throws RecognitionException {
		PopelContext _localctx = new PopelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_popel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NZINT) {
				{
				setState(44);
				factor();
				}
			}

			setState(47);
			popname();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(48);
				loc();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(51);
				match(T__1);
				setState(52);
				id();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(NZINT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_la = _input.LA(1);
			if ( !(_la==ZERO || _la==NZINT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public PopnameContext popname() {
			return getRuleContext(PopnameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ReactionGrammarParser.EOF, 0); }
		public LocContext loc() {
			return getRuleContext(LocContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			popname();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(60);
				loc();
				}
			}

			setState(63);
			match(T__2);
			setState(64);
			expression(0);
			setState(65);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopnameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public PopnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopnameContext popname() throws RecognitionException {
		PopnameContext _localctx = new PopnameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_popname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocContext extends ParserRuleContext {
		public LocidxContext locidx() {
			return getRuleContext(LocidxContext.class,0);
		}
		public LocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterLoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitLoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitLoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocContext loc() throws RecognitionException {
		LocContext _localctx = new LocContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_loc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__3);
			setState(70);
			locidx();
			setState(71);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocidxContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public LocidxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locidx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterLocidx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitLocidx(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitLocidx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocidxContext locidx() throws RecognitionException {
		LocidxContext _localctx = new LocidxContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_locidx);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ZERO) | (1L << NZINT) | (1L << IDENT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryOpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EXP() { return getToken(ReactionGrammarParser.EXP, 0); }
		public TerminalNode LOG() { return getToken(ReactionGrammarParser.LOG, 0); }
		public TerminalNode SQRT() { return getToken(ReactionGrammarParser.SQRT, 0); }
		public TerminalNode SUM() { return getToken(ReactionGrammarParser.SUM, 0); }
		public TerminalNode THETA() { return getToken(ReactionGrammarParser.THETA, 0); }
		public TerminalNode ABS() { return getToken(ReactionGrammarParser.ABS, 0); }
		public UnaryOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableContext extends ExpressionContext {
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public VariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegationContext extends ExpressionContext {
		public TerminalNode SUB() { return getToken(ReactionGrammarParser.SUB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(ReactionGrammarParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(ReactionGrammarParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ReactionGrammarParser.MOD, 0); }
		public MulDivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(ReactionGrammarParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ReactionGrammarParser.SUB, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanOpContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(ReactionGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(ReactionGrammarParser.OR, 0); }
		public BooleanOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterBooleanOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitBooleanOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitBooleanOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExponentiationContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode POW() { return getToken(ReactionGrammarParser.POW, 0); }
		public ExponentiationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterExponentiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitExponentiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitExponentiation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketedContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BracketedContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterBracketed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitBracketed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitBracketed(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FactorialContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FactorialContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterFactorial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitFactorial(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitFactorial(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionContext extends ExpressionContext {
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends ExpressionContext {
		public Token val;
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public TerminalNode NNFLOAT() { return getToken(ReactionGrammarParser.NNFLOAT, 0); }
		public NumberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArraySubscriptContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArraySubscriptContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterArraySubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitArraySubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitArraySubscript(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(ReactionGrammarParser.EQ, 0); }
		public TerminalNode NE() { return getToken(ReactionGrammarParser.NE, 0); }
		public TerminalNode LT() { return getToken(ReactionGrammarParser.LT, 0); }
		public TerminalNode GT() { return getToken(ReactionGrammarParser.GT, 0); }
		public TerminalNode LE() { return getToken(ReactionGrammarParser.LE, 0); }
		public TerminalNode GE() { return getToken(ReactionGrammarParser.GE, 0); }
		public EqualityContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfThenElseContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IfThenElseContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterIfThenElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitIfThenElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new BracketedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(76);
				match(T__5);
				setState(77);
				expression(0);
				setState(78);
				match(T__6);
				}
				break;
			case 2:
				{
				_localctx = new ArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				match(T__7);
				setState(81);
				expression(0);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(82);
					match(T__8);
					setState(83);
					expression(0);
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(89);
				match(T__9);
				}
				break;
			case 3:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(91);
				match(IDENT);
				setState(92);
				match(T__5);
				setState(93);
				expression(0);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(94);
					match(T__8);
					setState(95);
					expression(0);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(101);
				match(T__6);
				}
				break;
			case 4:
				{
				_localctx = new UnaryOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				((UnaryOpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXP) | (1L << LOG) | (1L << SQRT) | (1L << SUM) | (1L << THETA) | (1L << ABS))) != 0)) ) {
					((UnaryOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(104);
				match(T__5);
				setState(105);
				expression(0);
				setState(106);
				match(T__6);
				}
				break;
			case 5:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				match(SUB);
				setState(109);
				expression(10);
				}
				break;
			case 6:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(IDENT);
				}
				break;
			case 7:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				((NumberContext)_localctx).val = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ZERO) | (1L << NZINT) | (1L << NNFLOAT))) != 0)) ) {
					((NumberContext)_localctx).val = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(144);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(142);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExponentiationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(114);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(115);
						match(POW);
						setState(116);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new MulDivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(117);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(118);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(119);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(120);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(121);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(122);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new EqualityContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(123);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(124);
						((EqualityContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE) | (1L << NE))) != 0)) ) {
							((EqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(125);
						expression(6);
						}
						break;
					case 5:
						{
						_localctx = new BooleanOpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(126);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(127);
						((BooleanOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((BooleanOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(128);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new IfThenElseContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(129);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(130);
						match(T__11);
						setState(131);
						expression(0);
						setState(132);
						match(T__1);
						setState(133);
						expression(3);
						}
						break;
					case 7:
						{
						_localctx = new ArraySubscriptContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(136);
						match(T__3);
						setState(137);
						expression(0);
						setState(138);
						match(T__4);
						}
						break;
					case 8:
						{
						_localctx = new FactorialContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(140);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(141);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(146);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0096\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\7"+
		"\5\'\n\5\f\5\16\5*\13\5\3\5\5\5-\n\5\3\6\5\6\60\n\6\3\6\3\6\5\6\64\n\6"+
		"\3\6\3\6\5\68\n\6\3\7\3\7\3\b\3\b\3\t\3\t\5\t@\n\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7"+
		"\rW\n\r\f\r\16\rZ\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\rc\n\r\f\r\16\rf"+
		"\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rs\n\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0091\n\r\f\r\16\r\u0094\13\r\3\r\2"+
		"\3\30\16\2\4\6\b\n\f\16\20\22\24\26\30\2\n\3\2#$\4\2#$&&\3\2\25\32\3\2"+
		"#%\3\2\21\23\3\2\17\20\3\2\35\"\3\2\33\34\2\u009f\2\32\3\2\2\2\4\37\3"+
		"\2\2\2\6!\3\2\2\2\b,\3\2\2\2\n/\3\2\2\2\f9\3\2\2\2\16;\3\2\2\2\20=\3\2"+
		"\2\2\22E\3\2\2\2\24G\3\2\2\2\26K\3\2\2\2\30r\3\2\2\2\32\33\5\4\3\2\33"+
		"\34\7\3\2\2\34\35\5\6\4\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \5\b\5\2 \5\3"+
		"\2\2\2!\"\5\b\5\2\"\7\3\2\2\2#(\5\n\6\2$%\7\17\2\2%\'\5\n\6\2&$\3\2\2"+
		"\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)-\3\2\2\2*(\3\2\2\2+-\7#\2\2,#\3\2\2"+
		"\2,+\3\2\2\2-\t\3\2\2\2.\60\5\f\7\2/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2"+
		"\2\61\63\5\22\n\2\62\64\5\24\13\2\63\62\3\2\2\2\63\64\3\2\2\2\64\67\3"+
		"\2\2\2\65\66\7\4\2\2\668\5\16\b\2\67\65\3\2\2\2\678\3\2\2\28\13\3\2\2"+
		"\29:\7$\2\2:\r\3\2\2\2;<\t\2\2\2<\17\3\2\2\2=?\5\22\n\2>@\5\24\13\2?>"+
		"\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\7\5\2\2BC\5\30\r\2CD\7\2\2\3D\21\3\2\2"+
		"\2EF\7&\2\2F\23\3\2\2\2GH\7\6\2\2HI\5\26\f\2IJ\7\7\2\2J\25\3\2\2\2KL\t"+
		"\3\2\2L\27\3\2\2\2MN\b\r\1\2NO\7\b\2\2OP\5\30\r\2PQ\7\t\2\2Qs\3\2\2\2"+
		"RS\7\n\2\2SX\5\30\r\2TU\7\13\2\2UW\5\30\r\2VT\3\2\2\2WZ\3\2\2\2XV\3\2"+
		"\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7\f\2\2\\s\3\2\2\2]^\7&\2\2^_\7"+
		"\b\2\2_d\5\30\r\2`a\7\13\2\2ac\5\30\r\2b`\3\2\2\2cf\3\2\2\2db\3\2\2\2"+
		"de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\t\2\2hs\3\2\2\2ij\t\4\2\2jk\7\b\2\2"+
		"kl\5\30\r\2lm\7\t\2\2ms\3\2\2\2no\7\20\2\2os\5\30\r\fps\7&\2\2qs\t\5\2"+
		"\2rM\3\2\2\2rR\3\2\2\2r]\3\2\2\2ri\3\2\2\2rn\3\2\2\2rp\3\2\2\2rq\3\2\2"+
		"\2s\u0092\3\2\2\2tu\f\n\2\2uv\7\24\2\2v\u0091\5\30\r\nwx\f\t\2\2xy\t\6"+
		"\2\2y\u0091\5\30\r\nz{\f\b\2\2{|\t\7\2\2|\u0091\5\30\r\t}~\f\7\2\2~\177"+
		"\t\b\2\2\177\u0091\5\30\r\b\u0080\u0081\f\6\2\2\u0081\u0082\t\t\2\2\u0082"+
		"\u0091\5\30\r\7\u0083\u0084\f\5\2\2\u0084\u0085\7\16\2\2\u0085\u0086\5"+
		"\30\r\2\u0086\u0087\7\4\2\2\u0087\u0088\5\30\r\5\u0088\u0091\3\2\2\2\u0089"+
		"\u008a\f\17\2\2\u008a\u008b\7\6\2\2\u008b\u008c\5\30\r\2\u008c\u008d\7"+
		"\7\2\2\u008d\u0091\3\2\2\2\u008e\u008f\f\13\2\2\u008f\u0091\7\r\2\2\u0090"+
		"t\3\2\2\2\u0090w\3\2\2\2\u0090z\3\2\2\2\u0090}\3\2\2\2\u0090\u0080\3\2"+
		"\2\2\u0090\u0083\3\2\2\2\u0090\u0089\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\31\3\2\2"+
		"\2\u0094\u0092\3\2\2\2\r(,/\63\67?Xdr\u0090\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}