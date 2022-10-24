// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ConditionGrammar.g4 by ANTLR 4.10.1
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
public class ConditionGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, SUM=5, MIN=6, MAX=7, ADD=8, SUB=9, MUL=10, 
		DIV=11, MOD=12, AND=13, OR=14, EQ=15, GT=16, LT=17, GE=18, LE=19, NE=20, 
		ZERO=21, NZINT=22, NNFLOAT=23, IDENT=24, COMMENT_SINGLELINE=25, COMMENT_MULTILINE=26, 
		WHITESPACE=27;
	public static final int
		RULE_expression = 0, RULE_popname = 1, RULE_loc = 2, RULE_locidx = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"expression", "popname", "loc", "locidx"
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

	@Override
	public String getGrammarFileName() { return "ConditionGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ConditionGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
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
	public static class PopContext extends ExpressionContext {
		public PopnameContext popname() {
			return getRuleContext(PopnameContext.class,0);
		}
		public LocContext loc() {
			return getRuleContext(LocContext.class,0);
		}
		public PopContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterPop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitPop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitPop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SUM() { return getToken(ConditionGrammarParser.SUM, 0); }
		public TerminalNode MIN() { return getToken(ConditionGrammarParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(ConditionGrammarParser.MAX, 0); }
		public UnaryOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends ExpressionContext {
		public Token val;
		public TerminalNode ZERO() { return getToken(ConditionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ConditionGrammarParser.NZINT, 0); }
		public TerminalNode NNFLOAT() { return getToken(ConditionGrammarParser.NNFLOAT, 0); }
		public NumberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitNumber(this);
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
		public TerminalNode MUL() { return getToken(ConditionGrammarParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(ConditionGrammarParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ConditionGrammarParser.MOD, 0); }
		public MulDivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitMulDiv(this);
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
		public TerminalNode ADD() { return getToken(ConditionGrammarParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ConditionGrammarParser.SUB, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitAddSub(this);
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
		public TerminalNode AND() { return getToken(ConditionGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(ConditionGrammarParser.OR, 0); }
		public BooleanOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterBooleanOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitBooleanOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitBooleanOp(this);
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
		public TerminalNode EQ() { return getToken(ConditionGrammarParser.EQ, 0); }
		public TerminalNode NE() { return getToken(ConditionGrammarParser.NE, 0); }
		public TerminalNode LT() { return getToken(ConditionGrammarParser.LT, 0); }
		public TerminalNode GT() { return getToken(ConditionGrammarParser.GT, 0); }
		public TerminalNode LE() { return getToken(ConditionGrammarParser.LE, 0); }
		public TerminalNode GE() { return getToken(ConditionGrammarParser.GE, 0); }
		public EqualityContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitEquality(this);
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
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterBracketed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitBracketed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitBracketed(this);
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
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new BracketedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(9);
				match(T__0);
				setState(10);
				expression(0);
				setState(11);
				match(T__1);
				}
				break;
			case SUM:
			case MIN:
			case MAX:
				{
				_localctx = new UnaryOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(13);
				((UnaryOpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUM) | (1L << MIN) | (1L << MAX))) != 0)) ) {
					((UnaryOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(14);
				match(T__0);
				setState(15);
				expression(0);
				setState(16);
				match(T__1);
				}
				break;
			case IDENT:
				{
				_localctx = new PopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				popname();
				setState(20);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(19);
					loc();
					}
					break;
				}
				}
				break;
			case ZERO:
			case NZINT:
			case NNFLOAT:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
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
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(37);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(25);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(26);
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
						setState(27);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(28);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(29);
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
						setState(30);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new EqualityContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(31);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(32);
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
						setState(33);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new BooleanOpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(34);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(35);
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
						setState(36);
						expression(4);
						}
						break;
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class PopnameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ConditionGrammarParser.IDENT, 0); }
		public PopnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterPopname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitPopname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitPopname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopnameContext popname() throws RecognitionException {
		PopnameContext _localctx = new PopnameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_popname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
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
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterLoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitLoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitLoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocContext loc() throws RecognitionException {
		LocContext _localctx = new LocContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_loc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__2);
			setState(45);
			locidx();
			setState(46);
			match(T__3);
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
		public TerminalNode ZERO() { return getToken(ConditionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ConditionGrammarParser.NZINT, 0); }
		public TerminalNode IDENT() { return getToken(ConditionGrammarParser.IDENT, 0); }
		public LocidxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locidx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).enterLocidx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConditionGrammarListener ) ((ConditionGrammarListener)listener).exitLocidx(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConditionGrammarVisitor ) return ((ConditionGrammarVisitor<? extends T>)visitor).visitLocidx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocidxContext locidx() throws RecognitionException {
		LocidxContext _localctx = new LocidxContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_locidx);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001b3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0015\b\u0000\u0001"+
		"\u0000\u0003\u0000\u0018\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000&\b\u0000\n\u0000\f\u0000)\t"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0000\u0001\u0000\u0004\u0000"+
		"\u0002\u0004\u0006\u0000\u0007\u0001\u0000\u0005\u0007\u0001\u0000\u0015"+
		"\u0017\u0001\u0000\n\f\u0001\u0000\b\t\u0001\u0000\u000f\u0014\u0001\u0000"+
		"\r\u000e\u0002\u0000\u0015\u0016\u0018\u00186\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0002*\u0001\u0000\u0000\u0000\u0004,\u0001\u0000\u0000\u0000"+
		"\u00060\u0001\u0000\u0000\u0000\b\t\u0006\u0000\uffff\uffff\u0000\t\n"+
		"\u0005\u0001\u0000\u0000\n\u000b\u0003\u0000\u0000\u0000\u000b\f\u0005"+
		"\u0002\u0000\u0000\f\u0018\u0001\u0000\u0000\u0000\r\u000e\u0007\u0000"+
		"\u0000\u0000\u000e\u000f\u0005\u0001\u0000\u0000\u000f\u0010\u0003\u0000"+
		"\u0000\u0000\u0010\u0011\u0005\u0002\u0000\u0000\u0011\u0018\u0001\u0000"+
		"\u0000\u0000\u0012\u0014\u0003\u0002\u0001\u0000\u0013\u0015\u0003\u0004"+
		"\u0002\u0000\u0014\u0013\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000"+
		"\u0000\u0000\u0015\u0018\u0001\u0000\u0000\u0000\u0016\u0018\u0007\u0001"+
		"\u0000\u0000\u0017\b\u0001\u0000\u0000\u0000\u0017\r\u0001\u0000\u0000"+
		"\u0000\u0017\u0012\u0001\u0000\u0000\u0000\u0017\u0016\u0001\u0000\u0000"+
		"\u0000\u0018\'\u0001\u0000\u0000\u0000\u0019\u001a\n\u0006\u0000\u0000"+
		"\u001a\u001b\u0007\u0002\u0000\u0000\u001b&\u0003\u0000\u0000\u0007\u001c"+
		"\u001d\n\u0005\u0000\u0000\u001d\u001e\u0007\u0003\u0000\u0000\u001e&"+
		"\u0003\u0000\u0000\u0006\u001f \n\u0004\u0000\u0000 !\u0007\u0004\u0000"+
		"\u0000!&\u0003\u0000\u0000\u0005\"#\n\u0003\u0000\u0000#$\u0007\u0005"+
		"\u0000\u0000$&\u0003\u0000\u0000\u0004%\u0019\u0001\u0000\u0000\u0000"+
		"%\u001c\u0001\u0000\u0000\u0000%\u001f\u0001\u0000\u0000\u0000%\"\u0001"+
		"\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000"+
		"\'(\u0001\u0000\u0000\u0000(\u0001\u0001\u0000\u0000\u0000)\'\u0001\u0000"+
		"\u0000\u0000*+\u0005\u0018\u0000\u0000+\u0003\u0001\u0000\u0000\u0000"+
		",-\u0005\u0003\u0000\u0000-.\u0003\u0006\u0003\u0000./\u0005\u0004\u0000"+
		"\u0000/\u0005\u0001\u0000\u0000\u000001\u0007\u0006\u0000\u00001\u0007"+
		"\u0001\u0000\u0000\u0000\u0004\u0014\u0017%\'";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}