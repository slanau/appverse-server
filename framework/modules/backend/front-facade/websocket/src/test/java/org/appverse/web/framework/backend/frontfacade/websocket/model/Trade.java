package org.appverse.web.framework.backend.frontfacade.websocket.model;/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Appverse Public License 
 Version 2.0 (“APL v2.0”). If a copy of the APL was not distributed with this 
 file, You can obtain one at http://www.appverse.mobi/licenses/apl_v2.0.pdf. [^]

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the conditions of the AppVerse Public License v2.0 
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.
 */

public class Trade {

 private String ticker;

 private int shares;

 private TradeAction action;

 private String username;


 public String getTicker() {
  return this.ticker;
 }

 public void setTicker(String ticker) {
  this.ticker = ticker;
 }

 public int getShares() {
  return this.shares;
 }

 public void setShares(int shares) {
  this.shares = shares;
 }

 public TradeAction getAction() {
  return this.action;
 }

 public void setAction(TradeAction action) {
  this.action = action;
 }

 public String getUsername() {
  return this.username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 @Override
 public String toString() {
  return "[ticker=" + this.ticker + ", shares=" + this.shares
          + ", action=" + this.action + ", username=" + this.username + "]";
 }


 public enum TradeAction {
  Buy, Sell;
 }

}
