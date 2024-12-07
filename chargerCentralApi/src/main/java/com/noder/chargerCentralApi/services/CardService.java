package com.noder.chargerCentralApi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.models.Card;
import com.noder.chargerCentralApi.repositories.CardRepository;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card updateCard(Long id, Card updatedCard) {
        Card existingCard = getCardById(id);
        existingCard.setEncryptedCardNumber(updatedCard.getEncryptedCardNumber());
        existingCard.setCardHolderName(updatedCard.getCardHolderName());
        existingCard.setLast4Digits(updatedCard.getLast4Digits());
        return cardRepository.save(existingCard);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

}
